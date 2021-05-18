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
          result(rowIndex)(k) = triangle(rowIndex)(k) + result(rowIndex - 1)(triangle(rowIndex - 1).length-1)
        else
          result(rowIndex)(k) = triangle(rowIndex)(k) + math.min(result(rowIndex - 1)(k), result(rowIndex - 1)(k - 1))
      }
    }

    result(length - 1).min
  }

  val array: List[List[Int]] = List(
    List(2),
    List(3, 4),
    List(6, 5, 7),
    List(4, 1, 8, 3))


  println(minimumTotal(array))
}
