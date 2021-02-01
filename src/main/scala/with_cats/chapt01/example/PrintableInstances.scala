package with_cats.chapt01.example

object PrintableInstances {
  // 实现各种 类型下的 format
  implicit val stringPrintable: Printable[String] = (value: String) => value
  implicit val intPrintable: Printable[Int]       = (a: Int) => a.toString

  implicit val catPrintable: Printable[Cat] = (cat: Cat) => {
    s"printable ${Printable.format(cat.name)} is a ${Printable.format(cat.age)} year-old ${Printable
      .format(cat.color)} cat."
  }
}
