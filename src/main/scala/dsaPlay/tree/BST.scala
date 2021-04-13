package dsaPlay.tree

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/*

    二分搜索树：
        是一个二叉树，其右子树值大于父节点，其左子树值小于父节点
        (不包含重复元素，如果插入的元素以及从在，则不作操作，原来的相同元素，还保持一切)

 */
class BST[E <: Comparable[E]] {

  case class Node[E](var left: Node[E], var right: Node[E], var value: E)

  var root: Node[E] = null
  var size: Int = 0

  def getSize: Int = size

  def isEmpty: Boolean = getSize == 0

  def add(e: E): Unit = {
    if (root == null) {
      root = Node(null, null, e)
    } else
      addInner(root, e)
    size += 1
  }

  def addInner(node: Node[E], e: E): Node[E] = {
    if (node == null) Node(null, null, e)
    else if (e.compareTo(node.value) > 0) {
      node.right = addInner(node.right, e)
      node
    } else if (e.compareTo(node.value) < 0) {
      node.left = addInner(node.left, e)
      node
    } else node
  }

  def contains(e: E): Boolean = containsInner(root, e)

  def containsInner(node: Node[E], e: E): Boolean = {
    if (node == null) false
    else if (e.compareTo(node.value) == 0) true
    else if (e.compareTo(node.value) > 0) containsInner(node.right, e)
    else if (e.compareTo(node.value) < 0) containsInner(node.left, e)
    else false
  }

  private def preOrderInner(node: Node[E]): Unit = {
    if (node != null) {
      print(node.value + " ")
      preOrderInner(node.left)
      preOrderInner(node.right)
    }
  }

  def preOrder(): Unit = preOrderInner(root)

  private def postOrderInner(node: Node[E]): Unit = {
    if (node != null) {
      postOrderInner(node.left)
      postOrderInner(node.right)
      print(node.value + " ")
    }
  }

  def postOrder(): Unit = postOrderInner(root)

  private def inOrderInner(node: Node[E]): Unit = {
    if (node != null) {
      inOrderInner(node.left)
      print(node.value + " ")
      inOrderInner(node.right)
    }
  }

  def inOrder(): Unit = inOrderInner(root)

  def levelOrderInner(root: Node[E]): ArrayBuffer[E] = {
    val queue: mutable.Queue[Node[E]] = new mutable.Queue[Node[E]]()
    val levelOrderResult: ArrayBuffer[E] = new ArrayBuffer[E]()

    val dummyRoot = root
    if (dummyRoot != null)
      queue += dummyRoot

    while (!queue.isEmpty) {
      val ele = queue.dequeue()
      levelOrderResult.append(ele.value)

      if (ele.left != null)
        queue += ele.left

      if (ele.right != null)
        queue += ele.right
    }
    levelOrderResult
  }

  def levelOrder: Unit = {
    val es = levelOrderInner(root)
    println(es.mkString(", "))
  }

  def getMinInner(node: Node[E]): Option[Node[E]] = if (node.left == null) Some(node) else getMinInner(node.left)

  def getMin: Option[E] = if (root == null) None else Some(getMinInner(root).get.value)

  def getMaxInner(node: Node[E]): Option[E] = if (node.right == null) Some(node.value) else getMaxInner(node.right)

  def getMax: Option[E] = if (root == null) None else getMaxInner(root)

  private def removeMinInner(node: Node[E]): Node[E] = {
    if (node.left == null) {
      val lastRightNode = node.right
      node.right = null
      size -= 1
      lastRightNode
    } else {
      node.left = removeMinInner(node.left)
      node
    }
  }

  def removeMin: Option[E] = {
    if (root == null) None
    else {
      val min = getMin
      removeMinInner(root.left)
      min
    }
  }

  private def removeMaxInner(node: Node[E]): Node[E] = {
    if (node.right == null) {
      val lastLeftNode = node.left
      node.left = null
      size -= 1
      lastLeftNode
    } else {
      node.right = removeMaxInner(node.right)
      node
    }
  }

  def removeMax: Option[E] = {
    if (root == null) None
    else {
      val max = getMax
      removeMaxInner(root.right)
      max
    }
  }

  def removeInner(node: Node[E], e: E): Node[E] = {
    if (e.compareTo(node.value) > 0) {
      node.right = removeInner(node.right, e)
      node
    } else if (e.compareTo(node.value) < 0) {
      node.left = removeInner(node.left, e)
      node
    } else {
      // 是叶子节点
      if (node.left == null) {
        val lastRightNode = node.right
        node.right = null
        size -= 1
        lastRightNode
      }
      else if (node.right == null) {
        val lastLeftNode = node.left
        node.left = null
        size -= 1
        lastLeftNode
      } else {
        val currentMinNode: Node[E] = getMinInner(node.right).get
        removeMinInner(node.right)

        currentMinNode.left = node.left
        currentMinNode.right = node.right

        node.right = null
        node.right = null
        currentMinNode
      }

      // 不是叶子节点，左边有 node，右边没有

      // 不是叶子节点，右边有 node，左边没有
    }
  }

  def remove(e: E): Unit = {
    removeInner(root, e)
  }
}

object BST extends App {

  val bst: BST[Integer] = new BST[Integer]
  //  testBSTAdd
  //  testBSTContains
  //  testPrintOrder
  //  testGetMinAndMax

  //  testRemoveMinOne
  //  testRemoveMaxOne

  testRemoveEle

  def testBSTAdd(): Unit = {
    bst.add(5)
    bst.add(2)
    bst.add(7)

    bst.add(1)
    bst.add(3)
    bst.add(4)
    bst.add(9)
    bst.add(8)

    println("A")
  }

  def testBSTContains: Unit = {
    testBSTAdd

    println(bst.contains(4) == true)
    println(bst.contains(3) == true)
    println(bst.contains(6) == true)
    println(bst.contains(69) == false)
  }

  def testPrintOrder(): Unit = {
    testBSTAdd

    println("preOrderInner")
    bst.preOrder()
    println()

    println("inOrderInner")
    bst.inOrder()
    println()


    println("postOrderInner")
    bst.postOrder()
    println()

    println("levelOrderInner")
    bst.levelOrder
  }

  def testGetMinAndMax(): Unit = {

    testBSTAdd
    println(bst.getMin)
    println(bst.getMax)
  }

  def testRemoveMinOne(): Unit = {
    testBSTAdd
    bst.levelOrder
    bst.removeMin
    bst.levelOrder
  }

  def testRemoveMaxOne(): Unit = {
    testBSTAdd
    bst.levelOrder
    println(bst.removeMax)
    bst.levelOrder
  }

  def testRemoveEle(): Unit = {
    testBSTAdd
    bst.levelOrder
    bst.remove(8)
    bst.levelOrder
  }
}
