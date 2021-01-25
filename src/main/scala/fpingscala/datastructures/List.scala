package fpingscala.datastructures

/*

  ---
  List： 是一个接口，有2个实现，如果是空 list Nil 实现，否则使用 Cons实现(construct)
    Nil
    Cons
    List('1','b') <==> Cons('1', Cons('2', Nil))

  请在这里注意函数式子编程中当进行数据删除和数据添加的时候的数据共享理念
    Cons('1', Cons('2', Nil)) 删除=> 返回 Cons('2', Nil)
    Cons('1', Cons('2', Nil)) 添加=> 返回 Cons(3,Cons('1', Cons('2', Nil)))

  https://github.com/fpinscala/fpinscala/blob/master/answers/src/main/scala/fpinscala/datastructures/List.scala
 */
sealed trait List[+A]

case object Nil extends List[Nothing] // 空 list 表示
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = if (as.isEmpty) Nil else Cons(as.head, apply(as.tail: _*))

  def tail[A](ls: List[A]): List[A] = ls match {
    case Nil => Nil
    case Cons(_, t) => t
  }

  def setHead[A](l: List[A], h: A): List[A] = l match {
    case Nil => Cons(h, Nil)
    case Cons(_, t) => Cons(h, t)
  }

  def drop[A](l: List[A], n: Int): List[A] = {
    if (n <= 0)
      l
    else l match {
      case Nil => Nil
      case Cons(_, t) => drop(t, n - 1)
    }
  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Cons(h, t) if (f(h)) => dropWhile(t, f)
    case _ => l
  }

  // 使用 参数分组，可以更好的完成类型推导，在定义f 作为匿名函数传递的时候，可以不指定类型，
  // 但是参数不分组，那么需要写类型的
  def dropWhileV2[A](l: List[A])(f: A => Boolean): List[A] = l match {
    case Cons(h, t) if f(h) => dropWhileV2(t)(f)
    case _ => l
  }

  def append[A](a1: List[A], a2: List[A]): List[A] = a1 match {
    case Nil => a2
    case Cons(h, t) => Cons(h, append(t, a2))
  }

  // 任何需要操作到 最后一个元素的操作，都需要遍历到尾巴，并且将尾巴之前的数据做复制
  def init[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(_, Nil) => Nil
    case Cons(h, t) => Cons(h, init(t)) // 尾巴之前的元素全部都做了复制
  }

  def foldRight[A, B](l: List[A], z: B)(f: (A, B) => B): B = l match {
    case Nil => z
    case Cons(h, t) => f(h, foldRight(t, z)(f)) // 这里不是尾递归，在 foldRight 计算完成返回之后还需要进行 f 的函数计算
  }

  def sumV2(l: List[Int]): Int = foldRight(l, 0)((a, b) => a + b)

  def productV2(l: List[Double]): Double = foldRight(l, 1.0)((a, b) => a * b)

  def length[A](as: List[A]): Int = foldRight(as, 0)((_, acc) => acc + 1)

  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(h, t) => foldLeft(t, f(z, h))(f) // 这里就是尾递归了 foldLeft 返回之后就可以直接返回了
  }

  def sumV3(as: List[Int]): Int = foldLeft(as, 0)(_ + _)

  def productV3(as: List[Double]): Double = foldLeft(as, 1.0)(_ * _)

  def lengthV3[A](as: List[A]): Int = foldLeft(as, 0)((acc, _) => 1 + acc)

  // 这里会有元素的复制发生
  def reverse[A](as: List[A]): List[A] = foldLeft(as, List[A]())((acc, h) => Cons(h, acc))

  def appendV1[A](a1: List[A], a2: List[A]): List[A] = foldLeft(a1, a2)((acc, h) => Cons(h, acc))

  def appendV2[A](a1: List[A], a2: List[A]): List[A] = foldRight(a1, a2)((acc, h) => Cons(acc, h))

  def concat[A](l: List[List[A]]): List[A] = foldRight(l, Nil: List[A])(append)

  def plusOne(as: List[Int]): List[Int] = foldRight(as, Nil: List[Int])((h, t) => Cons(h + 1, t))

  def toString(as: List[Double]): List[String] = foldRight(as, Nil: List[String])((h, t) => Cons(h.toString, t))

  def map[A, B](as: List[A])(f: A => B): List[B] = foldRight(as, Nil: List[B])((h, t) => Cons(f(h), t))

  def filter[A](as: List[A])(f: A => Boolean): List[A] = foldRight(as, Nil: List[A])((h, t) => if (f(h)) Cons(h, t) else t)

  def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] = concat(map(l)(f))

  def addPairwise(a: List[Int], b: List[Int]): List[Int] = (a, b) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1+h2, addPairwise(t1, t2))
  }
}
