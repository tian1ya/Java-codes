package dsa.sort

/*
    内部排序法，是对于预排序的元素以插入的方式寻找该元素的适当位置，以达到排序的目的

    基本思想： 将n个待排序的元素看成为一个有序表进和一个无序表
    开始是有续表中只有一个元素，无序表中有n-1个元素，
    排序过程中每次从无序表中取出第一个元素，把它的排序码依次和有序表
    的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表

    效率高于 select
    速度提升：
      1. 没有发生交换
      2. 每次插入，新的arr是有序的，所以在找插入的位置会快
 */
class insert {

  def sort(array: Array[Int]): Unit = {

    //    // 第一轮
    //    // (101), 34,119,1 => (34 ,101) ,119,1
    //    var insertVal = array(1)
    //    var insertIndex = 1-1 // 有序表的最后这个元素的下标
    //
    //    // 还没有找到位置
    //    while (insertIndex >= 0 && insertVal < array(insertIndex)) {
    //      array(insertIndex + 1) = array(insertIndex)
    //      insertIndex -= 1
    //    }
    //    // 退出循环，找到插入地方
    //    array(insertIndex + 1) = insertVal
    //
    //    // 第二轮
    //    insertVal = array(2)
    //    insertIndex = 2-1 // 有序表的最后这个元素的下标
    //
    //    // 还没有找到位置
    //    while (insertIndex >= 0 && insertVal < array(insertIndex)) {
    //      array(insertIndex + 1) = array(insertIndex)
    //      insertIndex -= 1
    //    }
    //    // 退出循环，找到插入地方
    //    array(insertIndex + 1) = insertVal
    //    //
    //
    //    // 第三轮
    //    insertVal = array(3)
    //    insertIndex = 3-1 // 有序表的最后这个元素的下标
    //
    //    // 还没有找到位置
    //    while (insertIndex >= 0 && insertVal < array(insertIndex)) {
    //      array(insertIndex + 1) = array(insertIndex)
    //      insertIndex -= 1
    //    }
    //    // 退出循环，找到插入地方
    //    array(insertIndex + 1) = insertVal

    for (i <- 1 until array.length) {
      var insertVal = array(i)
      var insertIndex = i - 1

      // 安装次序大小，移动新arr里面的之前放进去的元素, (如果这里 < 变为 >, 那么排序就反了过来
      while (insertIndex >= 0 && insertVal > array(insertIndex)) {
        array(insertIndex + 1) = array(insertIndex)
        insertIndex -= 1
      }
      // 挑出while，表示已经找到新元素将要插入的位置, 注意这里 +1的含义，上面挑出while 的
      // insertVal >= array(insertIndex), 那么此时 insertVal 应该是插到 array(insertIndex) 之后，所以 insertIndex + 1
      array(insertIndex + 1) = insertVal
      println(s"第${i}轮arr")
      println(array.mkString(", "))
    }
  }
}

object insert extends App {
  val insert = new insert
  val arr = Array(101, 34, 119, 1, 3,5,1,9,0,-1)
  insert.sort(arr)
}
