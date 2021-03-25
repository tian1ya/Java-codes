package with_cats.chapt01

import with_cats.chapt01.JsonAst.Json
import with_cats.chapt01.JsonWriterInstances._


object Json extends App {
  def toJson[A](value: A)(implicit w:JsonWriter[A]):Json = w.write(value)

  private val json: Json = toJson(Person("Dave", "Dave@qq.com"))
  println(json)

  private val json1: Json = toJson("hello scala")
  println(json1)
}
