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
    case Cons(0.0, _) => 0.0
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

  def append[A](a1: List[A], a2: List[A]): List[A] = a1 match {
    case Nil => a2
    case Cons(h, t) => Cons(h, append(t, a2))
  }

  // 任何需要操作到 最后一个元素的操作，都需要遍历到尾巴，并且将尾巴之前的数据做复制
  def init[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(_, Nil) => Nil
    case Cons(h,t) => Cons(h, init(t)) // 尾巴之前的元素全部都做了复制
  }

}
