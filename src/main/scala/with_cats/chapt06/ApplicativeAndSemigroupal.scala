package with_cats.chapt06

import cats.Semigroupal

object ApplicativeAndSemigroupal extends App {

  private val maybeTuple: Option[(Int, String)] =
    Semigroupal[Option].product(Some(123), Some("abc"))
  println(maybeTuple) // Some((123,abc))

  private val maybeTuple1: Option[(Int, String)] =
    Semigroupal[Option].product(Some(123), None)
  println(maybeTuple1)
  // None
  // 如果其中有一个是None， 那么结果就是 None

  import cats.instances.option._

  private val maybeTuple2: Option[(Int, Int, Int)] = Semigroupal.tuple3(Option(1), Option(1), Option(1))
  println(maybeTuple2) // Some((1,1,1))

  private val maybeTuple21: Option[(Int, Int, Int)] = Semigroupal.tuple3(Option(1), Option(1), Option.empty)
  println(maybeTuple21) // None

  private val maybeInt: Option[Int] = Semigroupal.map2(Option(1), Option(1))(_+_)
  println(maybeInt) // Some(2)

  private val maybeInt1: Option[Int] = Semigroupal.map3(Option(1), Option(1), Option(1))(_+_+_)
  println(maybeInt1) // Some(3)

  import cats.instances.option._
  import cats.syntax.apply._
  private val tupled: Option[(Int, String)] = (Option(123), Option("123")).tupled
  println(tupled) // Some((123,123))
}
