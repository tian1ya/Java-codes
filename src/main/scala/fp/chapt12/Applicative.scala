package fp.chapt12

import fp.chapt11.Functor

//trait Applicative[F[_]] extends Functor[F] {
//  // 原语
//  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C]
//  def unit[A](a: => A):F[A]
//
//  // 衍生结合子
//  def map[A, B](fa: F[A])(f: A => B): F[B] = map2(fa, unit())((e, _) => f(e))
//  def traverse[A, B](as: List[A])(f:A =>F[B]):F[List[B]] =
//    as.foldRight(unit(List[B]()))((a, fbs) => map2(f(a), fbs)(_ :: _))
//
//  def sequence[A](fas: List[F[A]]): F[List[A]] =
//    traverse(fas)(fa => fa)
//
//  def replicateM[A](n: Int, fa: F[A]): F[List[A]] =
//    sequence(List.fill(n)(fa))
//
//  def apply[A,B](fab: F[A => B])(fa: F[A]): F[B] =
//    map2(fab, fa)(_(_))
//
//
//}

/*
  上面是书里面刚刚提到的使用 unit + map2 作为原语实现的，其实应该是 unit + apply 作为 Applicative 的原语的
 */

trait Applicative[F[_]] extends Functor[F] {
  def apply[A, B](fab: F[A => B])(fa: F[A]): F[B]

  def unit[A](a: => A): F[A]

  def map[A, B](fa: F[A])(f: A => B): F[B] = apply(unit(f))(fa)

  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C] = apply(map(fa)(f.curried))(fb)

  def map3[A, B, C, D](ma: F[A], mb: F[B], mc: F[C])(f: (A, B, C) => D): F[D] =
    map2(ma, map2(mb, mc)((b, c) => (b, c)))((a, bc) => f(a, bc._1, bc._2))
}

/*
   因为可以使用 flatMap 实现 map2 和 apply 所以 Monad 都是 applicative
 */

trait Monad[F[_]] extends Applicative[F] {

  def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B]

  def compose[A, B, C](f: A => F[B], g: B => F[C]): A => F[C] = a => flatMap(f(a))(g)

  def join[A](mma: F[F[A]]): F[A] = flatMap(mma)(a => a)
}



object Applicative extends App {

  val listApp = new Applicative[List] {
    override def unit[A](a: => A): List[A] = List(a)

    override def apply[A, B](fab: List[A => B])(fa: List[A]): List[B] = fab.flatMap(f => fa.map(f(_)))
  }

  private val ints: List[Int] = listApp.map(List(1, 2, 3))(a => a * 2)
  println(ints.mkString(","))

  private val ints1: List[Int] = listApp.map2(List(1, 2, 3), List(1, 2, 3))((a, b) => a + b)
  println(ints1.mkString(","))

  private val ints2: List[Int] = listApp.map3(List(1, 2, 3), List(1, 2, 3), List(1, 2, 3))((a, b, c) => a + b + c)
  println(ints2.mkString(","))

}
