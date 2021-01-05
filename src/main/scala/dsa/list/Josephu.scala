package dsa.list

/*
    约瑟夫问题：
    就是丢手帕游戏，有n个小孩围起来，约定编号为 1<=k<=n, 从1开始报数，数到m的时候出列，然后在从1开始数，数到m的时候在有一个人离开，
    直到剩下最后一个人

 */
class Josephu {

  var first: ChildNode = null

  def add(nums: Int): Unit = {

    if (nums < 1) {
      printf("%d must >= 1", nums)
      return
    }
    var cusor = first
    for (i <- 1 to nums) {
      val currentNode = new ChildNode(i)
      if (first == null) {
        first = currentNode
        first.next = first
        cusor = first
      } else {
        cusor.next = currentNode
        currentNode.next = first
        cusor = currentNode
      }
    }
  }

  def showChild(): Unit = {
    var cursor = first
    if (first == null) {
      printf("no child")
      return
    }
    while (cursor.next.no != first.no) {
      printf("Child %d\n", cursor.no)
      cursor = cursor.next
    }
    printf("Child %d\n", cursor.no)
  }


  def countChild(startNo: Int, cntNum: Int, numbers: Int): Unit = {
    /*
      startNo：开始数数地方
      cntNum: 数几下
      numbers: 总共多少人

      1. 注意单向列表，不能自我删除，需要有一个辅助指针(helper)，指向定位到first 前面
      2. first 指向starNum 位置，以及helper
      3. 开始数数 cntNum，并将first 和 helper 移动
      4. 删除 first 指向的节点
     */

    if (first == null ||  startNo < 1 || startNo > numbers) {
      throw new Exception("参数错误")
    }

    if (first.next == null) {
      println("只有一个人了")
      first
    }

    //    1. 注意单向列表，不能自我删除，需要有一个辅助指针(helper)，指向定位到first 前面
    var helperPointer: ChildNode = first
    while (helperPointer.next != first)
      helperPointer = helperPointer.next

    //    2. first 指向starNum 位置，以及helper
    for (i <- 0 until startNo - 1) {
      first = first.next
      helperPointer = helperPointer.next
    }

    //    3. 开始数数 cntNum，并将first 和 helper 移动
    for (i <- 0 until cntNum - 1) {
      first = first.next
      helperPointer = helperPointer.next
    }


    val deletedNode: ChildNode = first
    first = first.next
    helperPointer.next = first
    printf("\n小孩出圈%d\n\n", deletedNode.no)

  }
}

object Josephu {
  def main(args: Array[String]): Unit = {
    val josephu = new Josephu
    josephu.add(7)
    josephu.showChild()

//    val node = josephu.countChild(2, 2, 7)

    var a : Int = 7
    while (a != 1){
      a -= 1
      josephu.countChild(1, 2, 7)
    }

    josephu.showChild()
  }
}
