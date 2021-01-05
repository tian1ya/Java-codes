package dsa.tree


class Node(nNalue: Int) {
  var value: Int = nNalue
  var left: Node = null
  var right: Node = null

  def add(node: Node): Node = {
    if (node == null)
      new Node(-1)
    else if (node.value <= value) {
      //左边插入
      if (this.left == null) {
        this.left = node
        node
      } else {
        this.left.add(node)
      }
    } else {
      //右边插入
      if (this.right == null) {
        this.right = node
        node
      } else {
        this.right.add(node)
      }
    }
  }

  def show(value: Int): Unit = {
    printf("Node(value=%s)\n", value)
  }

  def inOrder(): Unit = {
    // left -> root -> right
    if (left != null) left.inOrder()
    show(value)
    if (right != null) right.inOrder()
  }

  def deleteLeaf(value: Int): Unit = {
    val targetNode = search(value)
    if (targetNode != null) {
      val targetParentNode = searchParent(value)
      if (targetNode.left == null && targetNode.right == null) {
        if (targetParentNode.left != null && targetParentNode.left.value == value) targetParentNode.left = null else targetParentNode.right = null
      } else if (targetNode.left != null && targetNode.right != null) {
        // 待删除的节点有子2个节点
        val smallestNodeInRight = deleteRightTreeMin(targetNode.right)
        targetNode.value = smallestNodeInRight

      } else {
        // 待删除只有一个节点
        // target Node 是 parent 的左还是右子节点
        if (targetNode.left != null) {
        // 左不为空
          if (targetParentNode.left.value == value) {
            targetParentNode.left = targetNode.left
          } else {
            targetParentNode.right == targetNode.left
          }
        } else {
        // 右不为空
          if (targetParentNode.left.value == value) {
            targetParentNode.left = targetNode.right
          } else {
            targetParentNode.right = targetNode.right
          }
        }
      }
    }
  }

  def search(value: Int): Node = {
    if (value == this.value) this
    else if (this.left != null && value <= this.value) this.left.search(value) // 往左边查找
    else if (this.right != null) this.right.search(value)
    else null
  }

  def searchParent(value: Int): Node = {
    /*
    1. 判断当前节点的左子节点或者右子节点是否是这个值
    */
    if (this.left != null && this.left.value == value || this.right != null && this.right.value == value) this
    else if (this.left != null && this.value >= value) this.left.searchParent(value)
    else if (this.right != null && this.value < value) this.right.searchParent(value)
    else null
  }

  /*

     1. 删除叶子节点
     2. 删除只有一个节点的
     3. 删除有2(多)个节点

     目前的二插排序树是单向的，删除一个节点，必须是在这个节点的父节点出操作，
     1. 找到要删除节点，
       1. 没有： 直接退出
       2. 有：继续往下走，找到该节点的父节点
       3. 删除

     2.
     3. 删除含有左右节点
       1. 找到待删除元素的右子树的最小值(删除)，也就是左子树的最大值
       2. 删除最小值
       3. 将1 步骤中找到的右子树最小值替换2步骤中删除了的节点，这个时候整体树还是有序的

  */

  def deleteRightTreeMin(node: Node): Int = {
    // 这里传入的 node 是他的父节点的 右树
    var target = node
    while (target.left != null)
      target = target.left
    deleteLeaf(target.value)
    target.value
  }

}

//
class BinarySortTree {
  var root: Node = null

  def add(node: Node): Unit = {
    if (root == null) {
      root = node
    } else {
      root.add(node)
    }
  }

  def showSort(): Unit = {
    if (root != null) root.inOrder() else ""
  }


  def deleteLeaf(value: Int): Unit = {
    if (root != null) root.deleteLeaf(value)
  }

  def search(value: Int): Node = {
    if (root != null) root.search(value) else null
  }

  def searchParent(value: Int): Node = {
    if (root != null) root.searchParent(value) else null
  }

}

object BinarySortTree extends App {
  private val array = Array(7, 3, 10, 12, 5, 1, 9, 2)
  private val sortTree = new BinarySortTree

  for (elem <- array) {
    sortTree.add(new Node(elem))
  }


  sortTree.showSort()
//  sortTree.deleteLeaf(2)
//  println("删除后")
//  sortTree.showSort()

//  sortTree.deleteLeaf(1)
//  println("删除后")
//  sortTree.showSort()

  sortTree.deleteLeaf(7)
  println("删除后")
  sortTree.showSort()

  sortTree.deleteLeaf(10)
  println("删除后")
  sortTree.showSort()
}


