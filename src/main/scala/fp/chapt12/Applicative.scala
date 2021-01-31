package fp.chapt12

import fp.chapt11.Functor

trait Applicative[F[_]] extends Functor[F] {
  // 原语
  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C]
  def unit[A](a: => A):F[A]

  // 衍生结合子
  def map[A, B](fa: F[A])(f: A => B): F[B] = map2(fa, unit())((e, _) => f(e))
  def traverse[A, B](as: List[A])(f:A =>F[B]):F[List[B]] =
    as.foldRight(unit(List[B]()))((a, fbs) => map2(f(a), fbs)(_ :: _))

  def sequence[A](fas: List[F[A]]): F[List[A]] =
    traverse(fas)(fa => fa)

  def replicateM[A](n: Int, fa: F[A]): F[List[A]] =
    sequence(List.fill(n)(fa))

  def apply[A,B](fab: F[A => B])(fa: F[A]): F[B] =
    map2(fab, fa)(_(_))


}

object Applicative {

}
