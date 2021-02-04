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

  private val ints1: List[Int] = Functor[List].map(ints)(a => a * 3)

  // lift 方法 F[A] => F[B]
  private val function: Option[Int] => Option[Int] = Functor[Option].lift((x:Int) => x + 1)
  println(function(Option(1)))


  // 这里的 map 函数来源于 functor，因为你这里找到了隐式超参数 Functor[F]
  def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] =
    start.map(n => n + 1)

  println(doMath(Option(12)))
  println(doMath(List(1,2,3)))

  implicit val optionFunctor:Functor[Box] = new Functor[Box] {
    override def map[A, B](fa: Box[A])(f: A => B): Box[B] = Box(f(fa.value))
  }

  private val value: Box[Int] = Box[Int](123)
  println(value.map(a => a*2))

  implicit val  treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = fa match {
      case Branch(left, right) => Branch(map(left)(f), map(right)(f))
      case Leaf(value) => Leaf(f(value))
    }
  }

//  println(Leaf(100).map(a => a * 2))
  // 这个时候编译器会出错，因为他只能识别 Functor[Tree] 而无法识别 Functor[Leaf] 和 Functor[Branch]

  def branch[A](left: Tree[A], right: Tree[A]):Tree[A] = Branch(left, right)
  def leaf[A](value: A): Tree[A] = Leaf(value)

  println(branch(Leaf(10), Leaf(20)).map(a => a*2))
  println(leaf(2).map(a => a*2))
}
