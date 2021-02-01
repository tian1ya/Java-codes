package with_cats.chapt01

import with_cats.chapt01.JsonAst.{JsNull, Json}
import with_cats.chapt01.JsonWriterInstances._

object JsonSyntax extends App {

//  implicit class JsonWriterOps[A](value: A) {
//    def toJson(implicit w: JsonWriter[A]): Json = w.write(value)
//  }

  implicit def optionWriter[A](implicit writer: JsonWriter[A]): JsonWriter[Option[A]] = new JsonWriter[Option[A]] {
    override def write(value: Option[A]): Json = value match {
      case Some(aValue) => writer.write(aValue)
      case None => JsNull
    }
  }

//  private val json: Json = Person("Dave", "Dave@qq.com").toJson
  // 等同于 private val json: Json = Person("Dave", "Dave@qq.com").toJson(personWriter)
  // 隐式类 implicit class JsonWriterOps[A](value: A) 这里使用了一个泛型
  // 意味着它给任何类都添加了一个 toJson 方法
  // 任何类都可以调用 toJson 这个方法
//  println(json)
  // JsObject(Map(name -> JsString(Dave), email -> JsString(Dave@qq.com)))

//  implicitly(JsonWriterOps[String])

  println(Json.toJson(Option("a String")))
  println(Json.toJson(Option(Person(name = "aa", email = "email"))))

}
