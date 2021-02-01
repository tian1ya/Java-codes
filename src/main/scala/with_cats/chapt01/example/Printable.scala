package with_cats.chapt01.example

trait Printable[B] {
  def format(value: B): String
}

object Printable {
  def format[A](a: A)(implicit printable: Printable[A]):String = printable.format(a) // 实现留给 后续需要的时候完成(extends)

  def print[A](a: A)(implicit printable: Printable[A]): Unit = println(format(a))
}
