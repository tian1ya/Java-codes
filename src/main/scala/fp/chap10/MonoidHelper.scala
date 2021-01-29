package fp.chap10


trait Monoid[A] {
  def op(a: A, b: A): A

  def zero: A
}

object Monoid {
  val initAddition: Monoid[Int] = new Monoid[Int] {
    override def op(a: Int, b: Int): Int = a + b

    override def zero: Int = 0
  }

  val intMultiplication: Monoid[Int] = new Monoid[Int] {
    override def op(a: Int, b: Int): Int = a * b

    override def zero: Int = 1
  }

  val booleanOr: Monoid[Boolean] = new Monoid[Boolean] {
    override def op(a: Boolean, b: Boolean): Boolean = a || b

    override def zero: Boolean = false
  }

  val booleanAnd: Monoid[Boolean] = new Monoid[Boolean] {
    override def op(a: Boolean, b: Boolean): Boolean = a && b

    override def zero: Boolean = true
  }

  def dual[A](m: Monoid[A]): Monoid[A] = new Monoid[A] {
    override def op(a: A, b: A): A = m.op(b, a)

    override def zero: A = m.zero
  }

  /*
    参数、返回值类型相同的函数被称为自函数（endofunction），为 endofunction 编写一个 monoid
    注意：两种函数组合方式都符合 monoid 法则，即 f compose g 和 f andThen g
   */
  def endoMonoid[A]: Monoid[A => A] = new Monoid[A => A] {
    override def op(a: A => A, b: A => A): A => A = a compose b

    override def zero: A => A = a => a
  }

  /**
   * Exercise 10.2 给出能够组合 Option 值的 monoid 实例
   *
   * 注意：
   *
   * 1. optionMonoid 的 op 方法有两种实现，即 a1 orElse a2 和 a2 orElse a1，两种实现都满足 monoid 法则，但两者并不等价；
   * 2. 这实际是个通用问题，每个 monoid 的 op 操作都有个顺序相反的同类，它们通常并不相同，但又同时满足 monoid 法则，都是合法实现；
   * 3. 而 booleanOr 和 booleanAnd 与其顺序相反的实现完全等价，这是因为 || 和 && 操作，既满足结合律，又满足交换律（monoid 法则未要求满足交换律）；
   */
  def optionMonoid[A]: Monoid[Option[A]] = new Monoid[Option[A]] {
    def op(a1: Option[A], a2: Option[A]): Option[A] = a1 orElse a2

    def zero: Option[A] = None
  }

  def firstOptionM[A]: Monoid[Option[A]] = optionMonoid[A]

  def lastOptionM[A]: Monoid[Option[A]] = dual(optionMonoid[A])

  def concatenate[A](l: List[A], m: Monoid[A]): A = l.foldRight(m.zero)((e, z) => m.op(e, z))

  def foldMap[A, B](l: List[A], m: Monoid[B])(f: A => B): B = l.foldRight(m.zero)((e, z) => m.op(z, f(e)))

  def foldMap2[A, B](l: List[A], m: Monoid[B])(f: A => B): B = l.map(x => f(x)).foldRight(m.zero)((a, b) => m.op(a, b))

  def foldMapV1[A, B](l: IndexedSeq[A], m: Monoid[B])(f: A => B): B = l.foldRight(m.zero)((e, z) => m.op(z, f(e)))

  def foldMap2V[A, B](l: IndexedSeq[A], m: Monoid[B])(f: A => B): B = l.map(x => f(x)).foldRight(m.zero)((a, b) => m.op(a, b))

  def foldMapV[A, B](indexedSeq: IndexedSeq[A], m: Monoid[B])(f: A => B): B = {
    if (indexedSeq.size <= 1) {
      indexedSeq.map(a => f(a)).headOption.getOrElse(m.zero)
    } else {
      val (l, r) = indexedSeq.splitAt(indexedSeq.size / 2)
      m.op(foldMapV(l, m)(f), foldMapV(r, m)(f))
    }
  }


  //  def par[A](m: Monoid[A]): Monoid[Par[A]] = new Monoid[Par[A]] {
  //    override def op(a: Par[A], b: Par[A]): Par[A] = map2(a, b)(m.op)
  //
  //    override def zero: Par[A] = unit(m.zero)
  //  }
  //
  //  def parFoldMap[A, B](v: IndexedSeq[A], m: Monoid[B])(f: A => B): Par[B] = foldMapV(v, par(m))(a => asyncF(f)(a))

  /*
    返回当前位置最小值，最大值以及是否排好序的
   */

  var sortedMonoid = new Monoid[Option[(Int, Int, Boolean)]] {
    override def op(a: Option[(Int, Int, Boolean)], b: Option[(Int, Int, Boolean)]): Option[(Int, Int, Boolean)] = (a, b) match {
      case (Some((x1, x2, x3)), Some((y1, y2, y3))) => Some(x1 max y1, x2 min y2, x3 && y3 && x2 <= y1)
      case (_, None) => None
      case (None, _) => None
    }

    override def zero: Option[(Int, Int, Boolean)] = None
  }

  def isSorted(indexedSeq: IndexedSeq[Int]): Option[(Int, Int, Boolean)] = foldMapV(indexedSeq, sortedMonoid)(a => Some(a, a, true))

  var sortedMonoidFoldRight = new Monoid[Option[(Int, Int, Boolean)]] {
    override def op(e: Option[(Int, Int, Boolean)], z: Option[(Int, Int, Boolean)]): Option[(Int, Int, Boolean)] = (e, z) match {
      case (Some((e1, e2, e3)), Some((z1, z2, z3))) => Some(z1 max e1, z2 min e2, z3 && e1 <= z1)
      case (Some((x1, x2, x3)), None) => Some((x1, x2, x3))
      case (None, Some((x1, x2, x3))) => Some((x1, x2, x3)) // 这两个主要是为  foldRight 只会出现 这里这种case，不会出现第二种的case
    }

    override def zero: Option[(Int, Int, Boolean)] = None
  }

  def isSortedFoldRight(indexedSeq: IndexedSeq[Int]): Option[(Int, Int, Boolean)] = foldMap2V(indexedSeq, sortedMonoidFoldRight)(a => Some(a, a, true))


  /**
   * 以下 ADT（代数数据结构）表示单词计数的部分结果：
   *
   * 1. Stub 是最简单的形式，表示还没有看到任何完整的单词
   * 2. Part 保存看到的完整单词的个数，lStub 保存左边的部分单词，rStub 保存右边的部分单词
   */
  sealed trait WC

  final case class Stub(char: String) extends WC

  final case class Part(lStub: String, words: Int, rStud: String) extends WC

  val wcMonoid: Monoid[WC] = new Monoid[WC] {
    override def op(a: WC, b: WC): WC = (a, b) match {
      case (Stub(a), Stub(b)) => Stub(a + b)
      case (Stub(a), Part(l, cnt, r)) => Part(a + l, cnt, r)
      case (Part(l, cnt, r), Stub(b)) => Part(l, cnt, r + b)
      case (Part(l, cnt, r), Part(l1, cnt1, r1)) => Part(l, cnt + cnt1 + (if ((r + l1).isEmpty) 0 else 1), r1)
    }

    override def zero: WC = Stub("")
  }

  def count(s: String): Int = {

    def aux(s: Char): WC = if (s.isWhitespace) Part("", 0, "") else Stub(s.toString)

    def unStub(s: String): Int = s.length min 1

    val wc = foldMapV(s.toIndexedSeq, wcMonoid)(aux(_))

    wc match {
      case Stub(s) => unStub(s)
      case Part(l, w, r) => unStub(l) + w + unStub(r)
    }
  }

  def productMonoid[A, B](aM: Monoid[A], bM: Monoid[B]): Monoid[(A, B)] = new Monoid[(A, B)] {
    override def op(a: (A, B), b: (A, B)): (A, B) = (aM.op(a._1, b._1), bM.op(a._2, b._2))

    override def zero: (A, B) = (aM.zero, bM.zero)
  }

  def functionMonoid[A, B](m: Monoid[B]): Monoid[A => B] = new Monoid[A => B] {
    override def op(f: A => B, g: A => B): A => B = a => m.op(f(a), g(a))

    override def zero: A => B = a => m.zero
  }


}

/**
 * 1. 很多数据结构都可以使用 foldLeft/foldMap 等函数折叠，比如 List/Tree/Stream/IndexedSeq 等，将该特性抽象为 Foldable 特质
 * 2. 具体实现时，foldLeft/foldRight 和 foldMap 可以互相实现
 * 3. F[_] 是高阶类型（higher-kinder type），它接受一个类型参数
 * List[T] => List[List[T]] => List[F[_]] 二阶泛型
 */

trait Foldable[F[_]] {

  import Monoid._

  def foldRight[A, B](as: F[A])(z: B)(f: (A, B) => B): B = foldMap(as)(f.curried)(endoMonoid[B])(z)

  def foldLeft[A, B](as: F[A])(z: B)(f: (B, A) => B): B = foldMap(as)(a => (b: B) => f(b, a))(dual(endoMonoid[B]))(z)

  def foldMap[A, B](as: F[A])(f: A => B)(mb: Monoid[B]): B = foldRight(as)(mb.zero)((a, b) => mb.op(f(a), b))

  def concatenate[A](as: F[A])(m: Monoid[A]): A = foldLeft(as)(m.zero)(m.op)

  def toList[A](as: F[A]): List[A] = foldRight(as)(List[A]())(_ :: _)
}

object ListFoldable extends Foldable[List] {
  override def foldRight[A, B](xs: List[A])(z: B)(f: (A, B) => B): B = xs.foldRight(z)(f)

  override def foldLeft[A, B](xs: List[A])(z: B)(f: (B, A) => B): B = xs.foldLeft(z)(f)

  override def foldMap[A, B](xs: List[A])(f: A => B)(m: Monoid[B]): B = xs.foldRight(m.zero)((a, b) => m.op(f(a), b))
}

object IndexedSeqFoldable extends Foldable[IndexedSeq] {
  override def foldRight[A, B](xs: IndexedSeq[A])(z: B)(f: (A, B) ⇒ B): B = xs.foldRight(z)(f)

  override def foldLeft[A, B](xs: IndexedSeq[A])(z: B)(f: (B, A) ⇒ B): B = xs.foldLeft(z)(f)

  override def foldMap[A, B](xs: IndexedSeq[A])(f: (A) ⇒ B)(m: Monoid[B]): B = Monoid.foldMapV(xs, m)(f)
}


object MonoidHelper extends App {
  private val res: Int = Monoid.initAddition.op(Monoid.initAddition.zero, 1)
  println(res)

  private val ress: Int = Monoid.initAddition.op(3, 1)
  println(ress)

  private val re: Int = Monoid.concatenate(List(1, 2, 3, 4), new Monoid[Int] {
    override def op(a: Int, b: Int): Int = a + b

    override def zero: Int = 0
  })
  println(re)

  private val reStr: String = Monoid.concatenate(List("1", "2", "3", "4"), new Monoid[String] {
    override def op(a: String, b: String): String = a + b

    override def zero: String = "0"
  })
  println("concatenate: " + reStr)

  val resss: Double = Monoid.foldMap(List(1, 2, 3, 4), new Monoid[Int] {
    override def op(a: Int, b: Int): Int = a + b

    override def zero: Int = 0
  })(a => a * 2)
  println("foldMap: " + resss)

  val resss2: Double = Monoid.foldMap2(List(1, 2, 3, 4), new Monoid[Int] {
    override def op(a: Int, b: Int): Int = a + b

    override def zero: Int = 0
  })(a => a * 2)
  println("foldMap2: " + resss2)
  //
  //  private val resssds: Double = Monoid.foldMapV(IndexedSeq(1, 2, 3, 4), new Monoid[Int] {
  //    override def op(a: Int, b: Int): Int = a + b
  //
  //    override def zero: Int = 0
  //  })(a => a * a)
  //

  //
  val maybeTupleTrue: Option[(Int, Int, Boolean)] = Monoid.isSorted(IndexedSeq(1, 2, 3, 4, 9, 14))
  println("isSorted: " + maybeTupleTrue)

  val maybeTupleFalse: Option[(Int, Int, Boolean)] = Monoid.isSorted(IndexedSeq(1, 2, 90, 4, 9, 14))
  println("isSorted: " + maybeTupleFalse)

  val maybeTupleFoldRightTrue: Option[(Int, Int, Boolean)] = Monoid.isSortedFoldRight(IndexedSeq(1, 2, 3, 4, 9, 14))
  println("isSortedFoldRight: " + maybeTupleFoldRightTrue)

  val maybeTupleFoldRightFalse: Option[(Int, Int, Boolean)] = Monoid.isSortedFoldRight(IndexedSeq(1, 2, 90, 4, 9, 14))
  println("isSortedFoldRight: " + maybeTupleFoldRightFalse)
  //
  //  println(Monoid.count("hello world ni hao"))

  val reseeee: Int = ListFoldable.foldLeft(List(1, 2, 3, 4))(0)((z, e) => z + e)
  val reseseee: Int = ListFoldable.foldRight(List(1, 2, 3, 4))(0)((e, z) => z + e)

  private val function: Int => Int = a => a
  private val resM: Int = ListFoldable.foldMap(List(1, 2, 3, 4))(function)(Monoid.initAddition)

  println(reseeee + " " + reseseee + " " + resM)

  private val productMonoid: Monoid[(Int, Int)] = Monoid.productMonoid(Monoid.initAddition, Monoid.intMultiplication)

  private val tuple: (Int, Int) = productMonoid.op((1, 2), (1, 2))
  println(tuple)

  private val tuple1: (Int, Int) = List(1, 2, 3, 4).foldRight(productMonoid.zero)((e, z) => (z._1 + e, z._2 * e))
  println(tuple1)

  private val fMonoid: Monoid[Int => Int] = Monoid.functionMonoid[Int, Int](Monoid.initAddition)
  private val intToInt: Int => Int = fMonoid.op(a => a * 2, a => a * 3)
  println(intToInt(10))


}
