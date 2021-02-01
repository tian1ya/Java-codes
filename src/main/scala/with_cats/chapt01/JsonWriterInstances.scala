package with_cats.chapt01

import with_cats.chapt01.JsonAst.{JsObject, JsString, Json}

object JsonAst {

  sealed trait Json

  final case class JsObject(get: Map[String, Json]) extends Json

  final case class JsString(get: String) extends Json

  final case class JsNumber(get: Double) extends Json

  case object JsNull extends Json

}


case class Person(name: String, email: String)

trait JsonWriter[A] {
  def write(value: A): Json
}


object JsonWriterInstances {
  implicit val personWriter: JsonWriter[Person] = (value: Person) => JsObject(Map(
    "name" -> JsString(value.name),
    "email" -> JsString(value.email)
  ))

  implicit val stringWriter: JsonWriter[String] = (value: String) => JsString(value)
}
