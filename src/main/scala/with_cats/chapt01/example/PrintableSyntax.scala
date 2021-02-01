package with_cats.chapt01.example

object PrintableSyntax {

  implicit class PrintableOps[A](a: A) {
    def format(implicit printable: Printable[A]): String = printable.format(a)

    def print(implicit printable: Printable[A]): Unit = println(format)
  }
}



