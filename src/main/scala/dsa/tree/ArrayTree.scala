package dsa.tree

class ArrayTree(val arr: Array[Int]) {

  def preOrder(): Unit = {
    this.preOrder(0)
  }

  def preOrder(index: Int): Unit = {
    if (arr == null || arr.isEmpty)
      throw new Exception("Empty arr can not lookup")
    println(arr(index))

    // 左递归遍历，
    if (leftChildNodeIndex(index) < arr.length)
      preOrder(leftChildNodeIndex(index))
    // 右递归遍历
    if (rightChildNodeIndex(index) < arr.length)
      preOrder(rightChildNodeIndex(index))
  }

  def leftChildNodeIndex(index: Int): Int = {
    index * 2 + 1
  }

  def rightChildNodeIndex(index: Int): Int = {
    index * 2 + 2
  }
}

object ArrayTree extends App {
  val arr = Array(1, 2, 3, 4, 5, 6, 7)

  private val tree = new ArrayTree(arr)
  tree.preOrder()
  // 1，2，4，5，3，6，7


  // 使用二叉排序树，优化一个数组完成查询和添加
}
