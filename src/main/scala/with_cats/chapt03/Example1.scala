package with_cats.chapt03

import cats.Functor
import cats.instances.list._
import cats.instances.function._
import cats.syntax.functor._

import scala.language.higherKinds


case class Box[A](value: A)

trait Tree[+A]

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

case class Leaf[A](value: A) extends Tree[A]


object Example1 extends App {

  private val ints = List(1, 2, 4)
  private val value: Functor[List] = Functor[List]
  // 根据类型，返回 ListInstances
  private val ints1: List[Int] = value.map(ints)(a => a * 3)
  /*
     然后在 ListInstances 中实现了最基础的 map 方法
     override def map[A, B](fa: List[A])(f: A => B): List[B] =
        fa.map(f)
     这里的 fa 就是我们传进去的 ints 的list， f 就是传进去的那个匿名函数
   */

  // def lift[A, B](f: A => B): F[A]
  private val function: Option[Int] => Option[Int] = Functor[Option].lift((x: Int) => x + 1)
  println(function(Option(1))) // Some(2)

  //
  // 这里的 map 函数来源于 functor，因为这里找到了隐式超参数 Functor[F]
  def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] = {
    val start1 = start
    start1.map(n => n + 1)
    /*
        函数会执行到 def map[B](f: A => B): F[B] = typeClassInstance.map[A, B](self)(f)
        这里 self 就是 start1， typeClassInstance 就是 functor 也就是 ListInstances
     */
  }
  //  println(doMath(Option(12))) // Some(13)
  println(doMath(List(1, 2, 3))) //  List(2, 3, 4)


  //
  //  private val future: Future[String] = Future(123).map(n => n + 1).map(n => n * 2).map(n => n + " !")
  //  Await.result(future, 1.second)
  //  println(future)
  //
  //
  //
    implicit val optionFunctor: Functor[Box] = new Functor[Box] {
      override def map[A, B](fa: Box[A])(f: A => B): Box[B] = Box(f(fa.value))
    }

    private val value1: Box[Int] = Box[Int](123)
    println(value1.map(a => a * 2))

    implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
      override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
        case Branch(left, right) => Branch(map(left)(f), map(right)(f))
        case Leaf(value) => Leaf(f(value))
      }
    }

//      println(Leaf(100).map(a => a * 2))
//     这个时候编译器会出错，因为他只能识别 Functor[Tree] 而无法识别
//     Functor[Leaf] 和 Functor[Branch]

    def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)

    def leaf[A](value: A): Tree[A] = Leaf(value)

    println(branch(Leaf(10), Leaf(20)).map(a => a * 2))
    println(leaf(2).map(a => a * 2))
}
