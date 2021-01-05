package fp.chap04

import scala.util.{Failure, Success, Try}

object OptionEitherTry {


  def main(args: Array[String]): Unit = {
    //    val stringInts = List("1","3","foo")
    //    val res = stringInts.map(l => toInt(l))
    //
    //    println(res)

    divide(2, 0) match {
      case Success(value) => print(value)
      case Failure(exception) => print(exception)
    }
  }

  def toInt(s: String): Option[Int] = {
    try {
      Some(Integer.parseInt(s.trim))
    } catch {
      case e: Exception => None
    }
  }

  def divide(x: Int, y: Int): Try[Int] = Try(x / y)

}
