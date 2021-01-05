package fp.chap05

import fp.chap05.Stream._

sealed trait Stream[+A] {
  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, _) => Some(h())
  }

  def toList: List[A] = this match {
    case Cons(h, t) => h() :: t().toList
    case _ => List()
  }

  def take(n: Int): List[A] = this match {
    case Cons(h, t) if n > 0 => h() :: t().take(n - 1)
    case _ => List()
  }

  def drop(n: Int): List[A] = this match {
    case Cons(_, t) if n > 0 => t().drop(n - 1)
    case _ => this.toList
  }

  def takeWhile4(p: A => Boolean): List[A] = this match {
    case Cons(h, t) if p(h()) => h() :: t().takeWhile4(p)
    case _ => List()
  }

  def exist(p: A => Boolean): Boolean = this match {
    case Cons(h, t) => p(h()) || t().exist(p)
    case _ => false
  }

  def existByFoldRight(p: A => Boolean): Boolean = foldRight(false)((a, b) => p(a) || b)

  // f: (A, => B) => B), 表示函数接收2个参数，第一个是类型A， 第二个是类型 => B, 然后生成结果 B
  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case Cons(t, h) => f(t(), h().foldRight(z)(f))
    case _ => z
  }

  def forAll(p: A => Boolean): Boolean = this match {
    case Cons(h, t) => p(h()) && t().forAll(p)
    case _ => true
  }

  def forAll1(p: A => Boolean): Boolean = foldRight(true)((a, b) => p(a) && b)

  def takeWhile3(f: A => Boolean): Stream[A] = foldRight(empty[A])((a, b) => if (f(a)) cons(a, b) else empty[A])

  def headOptionByFoldRight(): Option[A] = foldRight(None: Option[A])((h, _) => Some(h))


  def map[B](f: A => B): Stream[B] = foldRight(empty[B])((h, t) => cons(f(h), t))

  def filter(f: A => Boolean): Stream[A] = foldRight(empty[A])((h, t) => if (f(h)) cons(h, t) else t)

  def append[B >: A](st: => Stream[B]): Stream[B] = foldRight(st)((h, t) => cons(h, t))

  def flatMap[B](f: A => Stream[B]): Stream[B] = foldRight(empty[B])((h, t) => f(h) append t)

  def mapByUnfold[B](f: A => B): Stream[B] = unfold(this) {
    case Cons(h, t) => Some((f(h()), t()))
    case _ => None
  }

  def takeByUnfold(n: Int): Stream[A] = unfold((this, n)) {
    case (Cons(h, _), 1) => Some((h(), (empty, 0)))
    case (Cons(h, t), n) if n > 0 => Some(h(), (t(), n - 1))
    case _ => None
  }

  def takeWhile2(f: A => Boolean): Stream[A] = unfold(this) {
    case Cons(h, t) if f(h()) => Some(h(), t())
    case _ => None
  }

  def zipWith[B, C](a: Stream[B])(f: (A,B) => C): Stream[C] = unfold((this, a)) {
    case (Cons(ht,tt), Cons(ah, at)) => Some(f(ht(), ah()), (tt(),at()))
    case _ => None
  }

  def zipAll[B](s2: Stream[B]): Stream[(Option[A], Option[B])] =
    zipWithAll(s2)((_, _))

  def zipWithAll[B, C](s2: Stream[B])(f: (Option[A], Option[B]) => C): Stream[C] =
    Stream.unfold((this, s2)) {
      case (Empty, Empty)               => None
      case (Cons(h, t), Empty)          => Some(f(Some(h()), Option.empty[B]) -> Tuple2(t(), empty[B]))
      case (Empty, Cons(h, t))          => Some(f(Option.empty[A], Some(h())) -> (empty[A] -> t()))
      case (Cons(h1, t1), Cons(h2, t2)) => Some(f(Some(h1()), Some(h2())) -> (t1() -> t2()))
    }
}

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, t1: => Stream[A]): Stream[A] = {
    // 保持记忆，缓存下来，避免重复计算
    lazy val head = hd
    lazy val tail = t1

    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  // as 是一个Seq
  def apply[A](as: A*): Stream[A] = if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))

  def constant[A](a: A): Stream[A] = cons(a, constant(a))

  def from(n: Int): Stream[Int] = cons(n, from(n + 1))

  def fibs(): Stream[Int] = fibs(0, 1)

  private def fibs(a: Int, b: Int): Stream[Int] = cons(a, fibs(b, a + b))

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
      /*
          unfold 先对输入的第一个参数进行f操作，操作新产生一个结果，然后在这个结果作为输入 unfold 继续操作
       */
    case Some((h, s)) => cons(h, unfold(s)(f))
    case None => empty[A]
  }

  def fibsByUnfold(): Stream[Int] = unfold((0, 1)) { case (a, b) => Some(a, (b, a + b)) }

  def fromByUnfold(n: Int): Stream[Int] = unfold(n)(a => Some(a, a + 1))

  def constantByUnfold(n: Int): Stream[Int] = unfold(n)(a => Some(a, a))

  def ones(): Stream[Int] = constantByUnfold(1)


  def printStream[A](st: Stream[A]): Unit = st match {
    case Cons(h, t) => {
      print(h() + " ")
      printStream(t())
    }
    case _ => print("empty")
  }

  def main(args: Array[String]): Unit = {
    val st = Stream(1, 2, 7, 3, 4, 5, 6, 7, 8, 9)
    val listST = st.toList
    //    println(listST)

    val first2Ele = st.take(2)
    //    println(first2Ele)

    val drop2Ele = st.drop(2)
    //    println(drop2Ele)
//    val takeWhile3 = st.takeWhile(a => a < 5)
    //    print(takeWhile3)

    val esist = st.exist(a => a == 7)
    val esist2 = st.existByFoldRight(a => a == 7)
    //    print(esist2)

    val res11 = st.forAll(a => a < 7)
    val res2 = st.forAll1(a => a < 7)

    val head1 = st.headOptionByFoldRight()
    val head2 = st.headOption
    //    print(head1 == head2)
    //    print(head2)

    val unit = st.map(a => a * a)
    val unit2 = st.filter(a => a < 7)

    val value = st.append(Stream(10, 20, 30, 40))
    //    printStream(value)

    val Cons2 = constant(2)
    val is2Exist = Cons2.exist(a => a == 2)
    //    print(is2Exist)

    //    val from4Stream = from(4)
    //    val is4exist = from4Stream.exist(a => a == 3)
    //    print(is4exist)

    val fibs2 = fibs()
    val ints = fibs2.take(7)
    print(ints)
  }
}
