package fp.chapt11

/*
   确保返回值和参数保持一样的性质，所以定义一个Map 的接口，其定义不出太多的操作其作用并不是那么的显著，

   Monad
    关系单独的数据类型中寻找并定义最小的一个原始的操作集合，在这个之上通过组合定义更多可用的操作
    如我们定义了 unit 和 flatMap 为最小操作单元


 */
trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]

  def distributor[A, B](fab: F[(A, B)]): (F[A], F[B]) = (map(fab)(_._1), map(fab)(_._2))

  def codistributor[A, B](e: Either[F[A], F[B]]): F[Either[A, B]] = e match {
    case Left(fa) => map(fa)(Left(_))
    case Right(fa) => map(fa)(Right(_))
  }
}

trait Monad[F[_]] extends Functor[F] {
  def unit[A](a: => A): F[A]

  def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B]

  def map[A, B](ma: F[A])(f: A => B): F[B] = flatMap(ma)(e => unit(f(e)))

  def map2[A, B, C](ma: F[A], mb: F[B])(f: (A, B) => C): F[C] = flatMap(ma)(aa => map(mb)(bb => f(aa, bb)))

  def sequence[A](lma: List[F[A]]): F[List[A]] = lma.foldRight(unit(List[A]()))((e, z) => map2(e, z)(_ :: _))

  def traverse[A, B](la: List[A])(f: A => F[B]): F[List[B]] = la.foldRight(unit(List[B]()))((e, z) => map2(f(e), z)(_ :: _))
}

object MonadHelper extends App {

  val listFunctor = new Functor[List] {
    override def map[A, B](as: List[A])(f: A => B): List[B] = as map f
  }

  private val ints: List[Int] = listFunctor.map(List(1, 2, 3, 4))(z => z * 2)
  println(ints.mkString(","))

  /*
     到这里总算是能明白一些 Monad 对函数的定义API 的抽象总结能力了，所有的api 均是基于 unit 和 map 2个基本操作
     所以在 optionalMonad streamMonad 等均override 这2个函数
     而其他的基于这2个函数的api 均是在 Monad 的 trait 中实现， 这样在 optionalMonad streamMonad 就同时具有了这些API
     的能力。
   */
  val optionalMonad = new Monad[Option] {
    override def unit[A](a: => A): Option[A] = Some(a)

    override def flatMap[A, B](ma: Option[A])(f: A => Option[B]): Option[B] = ma flatMap f
  }

  val streamMonad = new Monad[Stream] {
    override def unit[A](a: => A): Stream[A] = Stream(a)

    override def flatMap[A, B](ma: Stream[A])(f: A => Stream[B]): Stream[B] = ma flatMap f
  }

  val listMonad = new Monad[List] {
    override def unit[A](a: => A): List[A] = List(a)

    override def flatMap[A, B](ma: List[A])(f: A => List[B]): List[B] = ma flatMap f
  }


}
