package with_cats.chapt04

import cats.Monad
import cats.instances.list._
import cats.instances.option._
import cats.syntax.flatMap._
import cats.syntax.functor._ // 一次性 import 上面的3个 syntax

//trait Monad[F[_]] {
//  def pure[A](value: A):F[A]
//  def flatMap[A,B](value: F[A])(f: A => F[B]):F[B]
//  def map[A,B](value: F[A])(f: A => B): F[B] = flatMap(value)(a => pure(f(a)))
//}

object MonadExample extends App {

  private val option: Option[Int] = Monad[Option].pure(2)

  println(Monad[Option].flatMap(option)(a => Some(a + 2))) // Some(4)
  println(Monad[Option].map(option)(a => a + 2)) // Some(4)

  // 注意上面 flatMap 中微山使用 map， 而不是继续使用flatMap
  def sumSquare[F[_] : Monad](a: F[Int], b: F[Int]): F[Int] =
    a.flatMap(aa => b.map(bb => bb * bb + aa * aa))


  def sumSquare2[F[_] : Monad](a: F[Int], b: F[Int]): F[Int] =
    for {
      x <- a
      y <- b
    } yield x*x + y*y


  println(sumSquare(Option(3), Option(4)))
  println(sumSquare2(Option(3), Option(4)))
  println(sumSquare(List(1, 2, 3), List(3, 4)))
  println(sumSquare2(List(1, 2, 3), List(3, 4)))

  // 但是不能直接使用一阶泛型
  // println(sumSquare(3,4))
  // 如果可以将一阶泛型和二阶泛型都可以使用， 使用Monoid 的Id Syntax
  import cats.Id
  println(sumSquare(3:Id[Int],4:Id[Int]))

  // 实现Id 的 pure、map、flatMap 方法
  def pure[A](value: A): Id[A] = value
  def map[A,B](initial: Id[A])(f: A=>B):Id[B] = f(initial)
  def flatMap[A, B](initial:Id[A])(f: A=> Id[B]): Id[B] = f(initial)


  import cats.syntax.either._
  private val value: Either[String, Int] = 3.asRight[String]
  // Either[A, B] 有2个类型参数，第一个参数是Left 的类型，第二个是Right 的类型
  // asRight 实现在 implicit class final class EitherIdOps[A](private val obj: A) 中，所以
  // 都具备了这个函数能力
  private val value1: Either[String, Int] = 4.asRight[String]

  println(value.flatMap(a => value1.map(b => a+b)))

//  private val value2: Right[String, Int] = Right(0)

  def countPositive(nums: List[Int]): Either[String, Int] = nums.foldRight(0.asRight[String])((e, z) => z.map(z => z + 1) )
  //  def countPositive(nums: List[Int]) = nums.foldRight(Right(0))((e, z) => z.map(z => z + 1) )
  // 这里编译失败

  val x = {
    println("Computation X")
    math.random
  }
  println(x)
  println(x)
  // 2次结果一样

  def y = {
    println("Computation y")
    math.random
  }
  // 2次结果不一样

  println(y)
  println(y)

  lazy val z = {
    println("C z")
    math.random
  }

  // 这个时候z 是不会有值的，代码块中的内容不会输出
  println(z)
  println(z)

  lazy val zz = {
    println("Computation zz")
    math.random
  }
  // 2次结果不一样

  println(zz)
  println(zz)


  import cats.Eval

  private val now: Eval[Double] = Eval.now(math.random() + 100) // 立刻计算，相当于是 val — eager and memoized:
  private val later: Eval[Double] = Eval.later(math.random() + 100) // lazy, memoized computaঞon, similar to a lazy val
  private val always: Eval[Double] = Eval.always(math.random() + 100) // a lazy computation, similar to a def:
  println("now: " + now.value)
  println("now: " + now.value)
  println("later: " + later.value)
  println("later: " + later.value)
  println("always: " + always.value)
  println("always: " + always.value)


  val zzzd = Eval.later{
    println("Computing Zzz")
    math.random()
  }
  println("Eval.later " + zzzd.value)
  println("Eval.later " + zzzd.value)


  val zzzdnow = Eval.now{
    println("Computing Zzz")
    math.random()
  }
  println("Eval.now " + zzzdnow.value)
  println("Eval.noe " + zzzdnow.value)

  val zzzdalways = Eval.always{
    println("Computing Zzz")
    math.random()
  }
  println("Eval.always " + zzzdalways.value)
  println("Eval.always " + zzzdalways.value)


  val greeting = Eval.always {
    println("Step 1")
    "Hello "
  }.map(str => {println("step 2"); s"$str world "})

  println(greeting.value)
  println(greeting.value)

  val saying = Eval
    .always {println("Step 1"); "The cat"}
    .map {str => println("Step 2"); s"$str sat on"}
    .memoize
    .map {str => println("Step 3"); s"$str the mat"}

  println(saying.value)
  println(saying.value)

}
