package fpingscala.chapt02

class TailOpt {

  def factorial(n: Int): Int = {

    def go(n: Int, acc: Int): Int = if (n <= 0) acc else go(n - 1, n * acc)

    go(n, 1)

  }

  def fib(n: Int): Int = {
    def go(n: Int, a: Int, b: Int): Int = if (n <= 1) b else go(n - 1, b, a + b)

    go(n, 0, 1)
  }
}

object TailOpt {
  def main(args: Array[String]): Unit = {

    val tailOpt = new TailOpt
    println(tailOpt.fib(3)) // 2
    println(tailOpt.fib(4)) // 3
    println(tailOpt.fib(5)) // 5
  }
}
