package dsaPlay.alg

object DP extends App {
  println(climbStairs(3))
  println(climbStairsV2(3))
  println(climbStairsV3(3))
  println(climbStairsV4(3))

  def climbStairs(n: Int): Int = {
    if (n == 0 || n == 1) {
      1
    } else if (n == 2) {
      2
    } else {
      val array: Array[Int] = new Array[Int](n)
      array(0) = 1
      array(1) = 2
      for (i <- 2 until n) {
        array(i) = array(i - 1) + array(i - 2)
      }
      array(n - 1)
    }
  }


  def climbStairsV2(n: Int): Int = {
    // 不记忆中间结果，性能超级差
    if (n < 3) {
      n
    } else {
      climbStairsV2(n - 1) + climbStairsV2(n - 2)
    }
  }

  def climbStairsV3(n: Int): Int = {
    def climbStairsV3Inner(n: Int, acc: Int, prev: Int): Int = {
      if (n == 0 || n == 1) {
        1
      } else if (n == 2) {
        acc
      } else {
        climbStairsV3Inner(n - 1, acc + prev, acc)
      }

    }

    climbStairsV3Inner(n, 2, 1)
  }

  def climbStairsV4(n: Int): Int = {
    if (n == 0 || n == 1) {
      1
    } else if (n == 2) {
      2
    } else {
      // foldRight 从右边开始往左边叠加，每一个元素和初始值运算，输出输出值的类型
      (2 until n).foldRight((2, 1))((e, z) => (z._2 + z._1, z._1))._1
    }
  }

  def minimumTotal(triangle: List[List[Int]]): Int = {
    val length = triangle.length

    val calcus = new Array[Int](length)

    calcus(0) = triangle(0)(0)

    for (i: Int <- 1 to length - 1) {
      calcus(i) = calcus(i - 1) + triangle(i)(i)
      for (j: Int <- i - 1 until 0 by -1) {
        calcus(j) = math.min(calcus(j - 1), calcus(j)) + triangle(i)(j)
      }
      calcus(0) = calcus(0) + triangle(i)(0)
    }

    calcus.min
  }


}
