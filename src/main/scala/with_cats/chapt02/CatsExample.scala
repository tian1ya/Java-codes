package with_cats.chapt02

import cats.Monoid
import cats.instances.int._
import cats.instances.option._
import cats.instances.string._
import cats.instances.map._
import cats.instances.tuple._

import cats.syntax.semigroup._

case class Order(totalCost: Double, quantity: Double)

object CatsExample extends App {

  def add(items: List[Int]): Int = items.foldRight(Monoid[Int].empty)(_ |+| _)

  println(add(List(1, 2, 3, 4)))

  // 这里可能会去计算那个类型就需要将那个类型的 instances import 进来到当前的上下文中
  def add2[A](items: List[A])(implicit monoid: Monoid[A]): A =
    items.foldRight(monoid.empty)((e, z) => monoid.combine(e, z))

  // combine 和 |+| 是一样的，一个 instance 一个 syntax
  def add3[A](items: List[A])(implicit monoid: Monoid[A]): A =
    items.foldRight(monoid.empty)((e, z) => e |+| z)


  println(add2(List(Option(1), Option(2), Option(3))))
  println(add3(List(Option(1), Option(2), Option(3))))
  println(add3(List(Some(1), Some(2), None, Some(3))))

  // println(add3(List(Some(1), Some(2), Some(3))))
  // 这里会出错，因为推断出来的类型是 List[Some(a:Int)]， 而Monoid 只接收 Option
  println(add3(List(1, 2, 3)))

  // 现在要将 case class Order 进行相加
  // 还是使用上面的 add3 方法，
  implicit val orderCombine: Monoid[Order] = new Monoid[Order] {
    override def empty: Order = Order(0.0, 0.0)

    override def combine(x: Order, y: Order): Order =
      Order(x.totalCost + y.totalCost, y.quantity + y.quantity)
  }

  println(add3(List(Order(1.0, 1.0), Order(2.0, 2.0), Order(3.0, 3.0))))

  println(Monoid[String].combine("a", "b"))
  private val value: Monoid[String] = Monoid.apply[String]
  println(value.combine("A", "b"))

  private val maybeInt = Option(12)
  private val maybeInt1 = Option(34)

  private val maybeInt2: Option[Int] = Monoid.combine(maybeInt, maybeInt1)
  println(maybeInt2)

  private val str: String = "Hi " |+| " there"
  private val i: Int = 1 |+| 2
  println(str)
  println(i)

  private val map: Map[String, Int] = Map("a" -> 1, "b" -> 2)
  private val map2: Map[String, Int] = Map("b" -> 3, "d" -> 4)

  private val stringToInt: Map[String, Int] = map |+| map2
  println(stringToInt)


  private val tuple: (String, Int) = ("hello", 12)
  private val tuple2: (String, Int) = ("word", 22)
  println(tuple |+| tuple2)

}
