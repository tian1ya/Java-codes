package fpingscala.streaming

import fpingscala.streaming.Stream._

sealed trait Stream[+A] {
  def headOption[A]: Option[A] = this match {
    case Cons(h, _) => Some(h())
    case Empty => None
  }

  def toList: List[A] = {

    def go(s: Stream[A], acc: List[A]): List[A] = s match {
      case Cons(h, t) => go(t(), h() :: acc)
      case _ => List()
    }

    go(this, List())
  }

  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if n > 1 => cons(h(), t().take(n - 1))
    case Cons(h, _) if n == 1 => cons(h(), empty)
    case _ => empty
  }

  def drop(n: Int): Stream[A] = this match {
    case Cons(_, t) if n > 0 => t().drop(n - 1)
    case _ => this
  }

  def takeWhile(p: A=> Boolean): Stream[A] = this match {
    case Cons(h, t) if p(h()) => cons(h(), t() takeWhile(p))
    case _ => this
  }

  def exist(p: A => Boolean): Boolean = this match {
    case Cons(h, t) => p(h()) || t().exist(p) // 如果 p(h())=true 那么后面的就不会去执行
    case _ => false
  }

  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case Cons(h,t) => f(h(), t().foldRight(z)(f))
    case _ => z
  }

  def existByFoldRight(p: A => Boolean): Boolean = foldRight(false)((e, z) => p(e) || z)

  def forAll(p: A => Boolean):Boolean = this match {
    case Cons(h,t) if !p(h())=> false
    case Cons(_, t) => t().forAll(p)
    case _ => true
  }

  def forAllByFoldRight(p: A=> Boolean): Boolean = foldRight(true)((e, z) => p(e) && z)



}

case object Empty extends Stream[Nothing]

case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd // lazy 只能和 val 一起使用，不能和 var 一起使用
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] = if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))


}