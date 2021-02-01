package with_cats.chapt01.example

import PrintableInstances._
import PrintableSyntax._
import with_cats.chapt01.example.CatShowInstances._
import with_cats.chapt01.example.CatEqInstances._
import cats.syntax.show._
import cats.syntax.eq._

object Run extends App {


  private val tomCat = Cat("Tom", 12, "red")
  private val tomCat2 = Cat("Tom", 12, "red")
  println(tomCat.format)

  tomCat.print

  Printable.print(tomCat)

  println(Printable.format(tomCat))

  println(tomCat == tomCat2)

  println(tomCat.show)

}
