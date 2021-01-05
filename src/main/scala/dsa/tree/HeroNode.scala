package dsa.tree

class HeroNode(hNo: Int, hName: String) {
  val no = hNo
  var name = hName
  var left: HeroNode = null
  var right: HeroNode = null

  def preOrder(): Unit = {
    //root -> left -> right
    show(no, name)
    if (left != null) left.preOrder()
    if (right != null) right.preOrder()
  }

  def inOrder(): Unit = {
    // left -> root -> right
    if (left != null) left.inOrder()
    show(no, name)
    if (right != null) right.inOrder()
  }

  def postOrder(): Unit = {
    // left -> root -> right
    if (left != null) left.postOrder()
    if (right != null) right.postOrder()
    show(no, name)
  }

  def show(no: Int, name: String): Unit = {
    printf("HeroNode(id=%s, name=%s)\n", no, name)
  }

  /*
    前序方式查找
    当前节点是否要查找的
    然后左边查找 -> 右边查找
   */
  def preOrderSearch(hNo: Int): HeroNode = {
    if (hNo == null) throw new Exception("id is empty")
    if (hNo == no) this
    else if (left != null) left.preOrderSearch(hNo)
    else if (right != null) right.preOrderSearch(hNo)
    else new HeroNode(-1, "null")
  }

  def inOrderSearch(hNo: Int): HeroNode = {
    // 先向左递归查找
    if (hNo == null) throw new Exception("id is empty")
    if (left != null) left.preOrderSearch(hNo)
    else if (hNo == no) this
    else if (right != null) right.preOrderSearch(hNo)
    else new HeroNode(-1, "null")
  }

  def postOrderSearch(hNo: Int): HeroNode = {
    // 先向左递归查找
    if (hNo == null) throw new Exception("id is empty")
    if (left != null) left.preOrderSearch(hNo)
    else if (right != null) right.preOrderSearch(hNo)
    else if (hNo == no) this
    else new HeroNode(-1, "null")
  }


  def simpleDelete(no: Int): Unit = {

    // 首先比较当前节点的左是否为要删除的节点
    if (this.left != null && this.left.no == no) this.left = getDeletedNextNode(this.left)
    // 然后是右子节点
    else if (this.right != null && this.right.no == no) this.right = getDeletedNextNode(this.right)
    // 然后左、右递归
    else if (this.left != null) this.left.simpleDelete(no)
    else if (this.right != null) this.right.simpleDelete(no)
  }

  /*
     如果要删除的A节点的删除，如果A 只有一个节点B， 那么就将节点B顶上
     如果要删除的A节点，有左节点B和右节点C，那么使用左节点B顶上
  */

  def getDeletedNextNode(currentNode: HeroNode): HeroNode = {
    // 左右都是空，那么随便返回一个，二者都是null
    if (currentNode.left == null && currentNode.right == null) currentNode.right
    // 只有左子树
    else if (currentNode.left != null && currentNode.right == null) currentNode.left
    else if (currentNode.left == null && currentNode.right != null) currentNode.right
    else {
      val rightNode = currentNode.right
      val leftNode = currentNode.left
      val leftRightNode = leftNode.right
      leftNode.right = rightNode
      rightNode.left = leftRightNode
      leftNode
    }
  }

}

class BinaryTree {
  var root: HeroNode = null

  def preOrder(): Unit = {
    if (root != null) root.preOrder()
  }

  def inOrder(): Unit = {
    if (root != null) root.inOrder()
  }

  def postOrder(): Unit = {
    if (root != null) root.postOrder()
  }

  def preOrderSearch(no: Int): HeroNode = {
    if (root != null) root.preOrderSearch(no) else throw new Exception("空树")
  }

  def inOrderSearch(no: Int): HeroNode = {
    if (root != null) root.inOrderSearch(no) else throw new Exception("空树")

  }

  def postOrderSearch(no: Int): HeroNode = {
    if (root != null) root.postOrderSearch(no) else throw new Exception("空树")
  }

  def simpleDelete(no: Int): Unit = {
    if (root.no == no) root = null else root.simpleDelete(no)
  }


}

object HeroNode extends App {
  val root = new HeroNode(1, "宋江")
  val node2 = new HeroNode(2, "无用")
  val node3 = new HeroNode(3, "卢俊义")
  val node4 = new HeroNode(4, "林冲")
  val node5 = new HeroNode(5, "关胜")

  val node5left = new HeroNode(6, "关胜left")
  val node5right = new HeroNode(7, "关胜right")


  root.left = node2
  root.right = node3
  node3.left = node5
  node3.right = node4

  node5.left = node5left
  node5.right = node5right

  val binaryTree = new BinaryTree
  binaryTree.root = root

  println("前序")
  binaryTree.preOrder()
  println("中序")
  binaryTree.inOrder()
  println("后序")
  binaryTree.postOrder()

  println("前序查找 id = 2")
  private val node: HeroNode = binaryTree.preOrderSearch(2)
  if (node != null) printf("HeroNode(id=%d, name=%s)\n", node.no, node.name)

  println("中序查找 id = 2")
  private val node1: HeroNode = binaryTree.inOrderSearch(2)
  if (node1 != null) printf("HeroNode(id=%d, name=%s)\n", node1.no, node1.name)

  println("后序查找 id = 2")
  private val node33: HeroNode = binaryTree.postOrderSearch(2)
  if (node33 != null) printf("HeroNode(id=%d, name=%s)\n", node33.no, node33.name)

    println("测试删除节点，删除前")
    binaryTree.postOrder()
    binaryTree.simpleDelete(3)
    println("删除后")
    binaryTree.postOrder()

  //  println("测试删除节点，删除前")
  //  binaryTree.postOrder()
  //  binaryTree.simpleDelete(3)
  //  println("删除后")
  //  binaryTree.postOrder()

  // no=1 是删除不掉的，这是单向链表，root 没有机会和自己比，始终都会和当前节点的下一个节点删除,
  // 简单的处理就是在删除操作进入之前就和 root.no 进行比较，如果相等就去删除
  //  println("测试删除节点，删除前")
  //  binaryTree.postOrder()
  //  binaryTree.simpleDelete(1)
  //  println("删除后")
  //  binaryTree.postOrder()
}
