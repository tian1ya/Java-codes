package fp.chap06

trait RNG {
  def nextInt: (Int, RNG)
}

case class SimpleRNG(seed: Long) extends RNG {
  override def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val int = (newSeed >>> 16).toInt
    (int, nextRNG)
  }

  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (i, r) = rng.nextInt
    (if (i < 0) -(i - 1) else i, r)
  }

  //Doule [0 - 1]
  def double(rng: RNG): (Double, RNG) = {
    val (i, r) = nonNegativeInt(rng)
    (i / Int.MaxValue.toDouble + 1, r)
  }

  def boolean(rng: RNG): (Boolean, RNG) =
    rng.nextInt match {
      case (i, rng2) => (i % 2 == 0, rng2)
    }


  def intDouble(rng: RNG): ((Int, Double), RNG) = {
    val (i, r) = nonNegativeInt(rng)
    val (d, r2) = double(r)
    ((i, d), r2)
  }

  def doubleInt(rng: RNG): ((Double, Int), RNG) = {
    val ((i, d), r) = intDouble(rng)
    ((d, i), r)
  }


  // 随机生成A类型值的类型
  // type 用来给类型或者是操作起别名
  type Rand[+A] = RNG => (A, RNG)
  val int: Rand[Int] = _.nextInt
  /*
      等同于
      val int2 :Rand[Int] = (rng) => rng.nextInt

      在使用Rand[+A] 的时候需要返回一个常量值，和一个 rng
   */
  //
  //  val _double: Rand[Double] =
  //    map(nonNegativeInt)(_ / (Int.MaxValue.toDouble + 1))

  def unit[A](a: A): Rand[A] = rng => (a, rng)

  def map[A, B](s: Rand[A])(f: A => B): Rand[B] = r => {
    val (value, rng) = s(r)
    (f(value), rng)
  }

  def map2[A, B, C](a: Rand[A], b: Rand[B])(f: (A, B) => C): Rand[C] = r => {
    val (aValue, r1) = a(r)
    val (bValue, r2) = b(r1)
    (f(aValue, bValue), r2)
  }

  def both[A, B](ra: Rand[A], rb: Rand[B]): Rand[(A, B)] = map2(ra, rb)((_, _))

  val randIntDouble: Rand[(Int, Double)] = both(nonNegativeInt, double)

  val randDoubleInt: Rand[(Double, Int)] = both(double, nonNegativeInt)

  def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B] = rang => {
    val (value, r) = f(rang)
    g(value)(r)
  }

  def nonNegativeLessThan(n: Int): Rand[Int] = flatMap(nonNegativeInt) {
    i =>
      val mod = i % n
      if (i + (n - 1) - mod >= 0) unit(mod) else nonNegativeLessThan(n)
  }

  def mapByFlatMap[A, B](r: Rand[A])(f: A => B): Rand[B] = flatMap(r)(a => unit(f(a)))

  def map2ByFlatMap[A, B, C](a: Rand[A], b: Rand[B])(f: (A, B) => (C)): Rand[C] = flatMap(a)(a => map(b)(b => f(a, b)))

}

// A 是值，S 表示状态

import fp.chap06.State._

case class State[S, +A](run: S => (A, S)) {

  // 返回一个 State{State 的实现：run: S => (A, S) }， 大括号里面就是run
  def flatMap[B](f: A => State[S, B]): State[S, B] = State {
    a =>
      val (aValue, s) = run(a)
      f(aValue).run(s)
  }

  //  def unit[S,A](a: A):State[S,A]  = State(s => (a, s))

  def map[B](f: A => B): State[S, B] = flatMap(a => unit(f(a)))

  def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] = flatMap(a => sb.map(b => f(a, b)))
}

object State {
  type Rand[A] = State[RNG, A]

  def unit[S, A](a: A): State[S, A] =
    State(s => (a, s))

  // The idiomatic solution is expressed via foldRight
  def sequenceViaFoldRight[S, A](sas: List[State[S, A]]): State[S, List[A]] =
    sas.foldRight(unit[S, List[A]](List()))((f, acc) => f.map2(acc)(_ :: _))

  def sequence[S, A](sas: List[State[S, A]]): State[S, List[A]] = {
    def go(s: S, actions: List[State[S, A]], acc: List[A]): (List[A], S) =
      actions match {
        case Nil => (acc.reverse, s)
        case h :: t => h.run(s) match {
          case (a, s2) => go(s2, t, a :: acc)
        }
      }

    State((s: S) => go(s, sas, List()))
  }

  object transparentRef {
    def main(args: Array[String]): Unit = {
      val rng = SimpleRNG(42)
      val (int11, nextGNG) = rng.nextInt
      //    println(int11)

      val mapFunc = rng.map(rng => (3, rng))(_ * 3)

      //    println(mapFunc(rng)._1)


      val map2Func = rng.map2(rng => (3, rng), rng => (2, rng))((a, b) => (a * b))
      //    println(map2Func(rng)._1)

      val nothFunc = rng.both(rng => (3, rng), rng => (2, rng))
      //    println(nothFunc(rng))

      val rollDie = rng.nonNegativeLessThan(6)
      val tuple = rollDie(SimpleRNG(5))
      println(tuple._1)

    }
  }

}
