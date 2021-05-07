package dsaPlay

object RecursiveSum extends App {

  def sum(eles: Array[Int], res: Int, index: Int): Int = if (index == eles.size) res else sum(eles, res + eles(index), index + 1)

  def fib(n: Int): Int = n match {
    case 0 => 0
    case 1 => 1
    case _ => fib(n - 1) + fib(n - 2)
  }

  /*
    f(0) = f(1) = 1
    f(n) = f(n-1) + f(n-2)
   */

  def fibV2(n: Int): Int = n match {
    case 0 => 0
    case 1 => 1
    case _ => fibDPInner(n, 0, 1)
  }
  def fibDPInner(n: Int, a: Int, acc: Int): Int = n match {
    case 1 => acc
    case _ => fibDPInner(n-1, acc, acc + a)
  }

//  print(sum(Array(1, 2, 3, 4), 0, 0))
  print(fibV2(5))
}
