package dsa.sort

import java.text.SimpleDateFormat
import java.util.Date

class bubble {
  def bubbleSort(arr: Array[Int]): Unit = {
    val length = arr.length
    for (i <- 0 until length) {
      for (j <- i until length) {
        if (arr(i) > arr(j))
          swap(arr, i, j)
      }
    }
  }

  def swap(arr: Array[Int], i: Int, j:Int): Unit = {
    val i1 = arr(i)
    arr(i) = arr(j)
    arr(j) = i1
  }
}

object bubble extends App {
  val bubb = new bubble

  val random = new util.Random()
  val arr = new Array[Int](8)
  for(i <- 0 until 8)
    arr(i) = random.nextInt(10)

  val value: Date = new Date()
  val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  val beforeSort = dateFormat.format(new Date())
  println("排序前时间=" + beforeSort)

  println(arr.mkString(", "))
  println("排序后")

  bubb.bubbleSort(arr)

  println(arr.mkString(", "))
  println("排序后时间")

  println(dateFormat.format(new Date()))
}

