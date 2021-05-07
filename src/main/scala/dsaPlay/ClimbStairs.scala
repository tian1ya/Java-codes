package dsaPlay

object ClimbStairs extends App {
  /*
    f(0) = f(1) = 1
    f(2) = 2
    f(n) = f(n-1) + f(n-2)
   */

  def climbStairs(n: Int): Int = n match {
    case 0 => 1
    case 1 => 1
    case 2 => 2
    case _ => climbStairsInner(n, 1, 2)
  }
  def climbStairsInner(i: Int, a: Int, acc: Int): Int = {
    if (i == 2) acc
    else climbStairsInner(i-1, acc, acc + a)
  }
}
