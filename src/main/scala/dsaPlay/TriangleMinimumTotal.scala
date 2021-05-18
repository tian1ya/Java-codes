package dsaPlay

object TriangleMinimumTotal extends App {
  def minimumTotal(triangle: List[List[Int]]): Int = {
    /*
      初始状态，dp 全为0

      状态转移

      dp[row][k] = min(dp[row-1][k], dp[row-1][k-1]) + triangle[row][k] when k != 0 or k != length
      dp[row][0] = triangle[row][0] + dp[row-1][0] when k=0
      dp[row][length-1] = triangle[row][length-1] + dp[row-1][length-1] when k=length-1
     */

    val length = triangle.length
    val result: Array[Array[Int]] = Array.ofDim[Int](length, length)
    result(0)(0) = triangle.head.head

    for (rowIndex <- 1 until length) {
      val currentLength = triangle(rowIndex).length
      for (k <- 0 until currentLength) {
        if (k == 0)
          result(rowIndex)(k) = triangle(rowIndex)(k) + result(rowIndex - 1).head
        else if (k == currentLength - 1)
          result(rowIndex)(k) = triangle(rowIndex)(k) + result(rowIndex - 1)(triangle(rowIndex - 1).length - 1)
        else
          result(rowIndex)(k) = triangle(rowIndex)(k) + math.min(result(rowIndex - 1)(k), result(rowIndex - 1)(k - 1))
      }
    }

    result(length - 1).min
  }

  def minimumTotalDPOP(triangle: List[List[Int]]): Int = {
    /*
      初始状态，dp 全为0

      状态转移

      dp[row][k] = min(dp[row-1][k], dp[row-1][k-1]) + triangle[row][k] when k != 0 or k != length
      dp[row][0] = triangle[row][0] + dp[row-1][0] when k=0
      dp[row][length-1] = triangle[row][length-1] + dp[row-1][length-1] when k=length-1
     */

    val dp = new Array[Int](triangle.length)
    dp(0) = triangle(0)(0)

    for (rowIndex <- 1 until triangle.length) {
      for (kIndex <- (0 until triangle(rowIndex).length).reverse) {
        if (kIndex == 0)
          dp(kIndex) = triangle(rowIndex)(kIndex) + dp.head
        else if (kIndex == triangle(rowIndex).length - 1)
          dp(kIndex) = triangle(rowIndex)(kIndex) + dp(triangle(rowIndex-1).length-1)
        else
          dp(kIndex) = triangle(rowIndex)(kIndex) + math.min(dp(kIndex), dp(kIndex - 1))
      }
    }
    dp.min
  }

  val array: List[List[Int]] = List(
    List(2),
    List(3, 4),
    List(6, 5, 7),
    List(4, 1, 8, 3))


  println(minimumTotal(array))
  println(minimumTotalDPOP(array))
}
