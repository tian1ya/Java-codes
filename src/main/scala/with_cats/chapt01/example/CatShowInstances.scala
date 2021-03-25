package with_cats.chapt01.example

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._

object CatShowInstances extends App {

  println(123.show)
  println("123".show)

  implicit val catShow: Show[Cat]= Show[Cat] { cat =>
    s"show ${cat.name.show} is a ${cat.age.show} year-old ${cat.color.show} cat." }

  private val cat = Cat("1", 12, "blue")
  println(cat.show)
}
