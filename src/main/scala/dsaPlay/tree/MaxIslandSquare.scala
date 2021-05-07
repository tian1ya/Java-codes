package dsaPlay.tree

object MaxIslandSquare extends App {
  val sea = Array[Array[Int]](
    Array(0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
    Array(0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0),
    Array(0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0),
    Array(0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0),
    Array(0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0)
  )

  val length = sea.length
  val rowLength = sea(0).length

  //  print(islandPerimeter(sea))
  print(numOfIsland(sea))

  def numOfIsland(grid: Array[Array[Int]]): Int = {
    var res: Int = 0

    for (rowIndex <- 0 until length) {
      for (index <- 0 until rowLength if grid(rowIndex)(index) == 1) {
        area(grid, rowIndex, index)
        res += 1
      }
    }
    res
  }

  def islandPerimeter(grid: Array[Array[Int]]): Int = {
    var res: Int = 0
    for (rowIndex <- 0 until length)
      for (index <- 0 until rowLength if grid(rowIndex)(index) == 1) {
        val i = area(grid, rowIndex, index)
        res = math.max(res, i)
      }
    res
  }

  def area(grid: Array[Array[Int]], i: Int, j: Int): Int = {
    if (!InArea(i, j)) return 0
    if (grid(i)(j) != 1) return 0
    grid(i)(j) = 2
    1 + area(grid, i - 1, j) + area(grid, i, j - 1) + area(grid, i + 1, j) + area(grid, i, j + 1)
  }

  def InArea(i: Int, j: Int): Boolean =
    i >= 0 && i < length && j >= 0 && j < rowLength
}
