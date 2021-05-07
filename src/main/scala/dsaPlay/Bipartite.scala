package dsaPlay


object Bipartite extends App {
  val graph: Array[Array[Int]] = Array(Array(1, 3), Array(0, 2), Array(1, 3), Array(0, 2))
  val V = graph.length
  val visited = Array.fill(V)(false)
  val colors = Array.fill(V)(-1)

  print(isBipartie)

  def isBipartie: Boolean = {

    for (index <- 0 until(V) if !visited(index)) {
      if (!dfs(index, 0))
        return false
    }
    true
  }

  def dfs(index: Int, color: Int): Boolean = {
    visited(index)=true
    colors(index) = color
    for (elem <- graph(index)) {
      if (!visited(elem)) {
        if (!dfs(elem, 1-color)) return false
      } else if (colors(index) == colors(elem)) return false
    }
    true
  }
}
