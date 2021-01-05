package dsa.sort

import java.text.SimpleDateFormat
import java.util.Date

import dsa.sort.bubble.bubb

/*
  属于内部排序法，是从预排序的数据中， 按指定的规则选出某元素
  ，经过和其他元素重整，在规定(小到大，还是大到小)交换位置后达到排序的目的

  思想：是一种简单的排序方法，基本思想是： 第一次从R[0]~R[n-1]中选出最小的元素，和R[0] 交换
  第二次从R[1] ~ R[n-1] 中选取最小值，和R[1] 交换，第三次从R[2]~R[n-1] 中选取最小值，和R[2] 交换
  第i 次从R[i-1]~R[n-1] 中选最小值，和R[i-1]交换，..... 第n-1次从R[n-2] ~ R[n-1] 中选最小值
  和R[n-1] 交换，总共通过n-1次，得到一个案排序码从小到大排序的有序序列

  效率高于 bubble
  提升原因
    1. 没有发生元素交换，而是赋值
 */
class select {

  def swap(arr: Array[Int], i: Int, j:Int): Unit = {
    val i1 = arr(i)
    arr(i) = arr(j)
    arr(j) = i1
  }

  def sort(arr: Array[Int]): Unit = {
    // 排序的演变过程
    //1. 第一轮 101,34,119,1 -> 1,101,34,119
//    var min = arr(0)
//    var minIndex = 0
//    // 遍历，从第二位开始
//    for (i <- 1 until arr.length) {
//      if (arr(minIndex) > arr(i)){
//        min = arr(i)
//        minIndex = i
//      }
//    }
//
//    // 判断是是需要重置(这里也可以交换，但是重置之间赋值，更快速)
//    if (minIndex != 0) {
//      arr(minIndex) = arr(0)
//      arr(0) = min
//    }


    for (i <- 0 until arr.length) {
      var min = arr(i)
      var minIndex = i

      // 每一次迭代找到当前最小值
      for (j <- i until arr.length) {
        if (arr(minIndex) > arr(j)){
          min = arr(j)
          minIndex = j
        }
      }

      // 如果确实最小值不是第一位，那么就将找到的最小值和第一位交换
      if (minIndex != i) {
        arr(minIndex) = arr(i)
        arr(i) = min
      }
    }
  }
}

object select extends App {
  val select1 = new select

  val random = new util.Random()
  val arr = new Array[Int](20)
  for(i <- 0 until 20)
    arr(i) = random.nextInt(10)

  val value: Date = new Date()
  val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  val beforeSort = dateFormat.format(new Date())
  println("排序前时间=" + beforeSort)

  println(arr.mkString(", "))
  println("排序后")

  select1.sort(arr)

  println(arr.mkString(", "))
  println("排序后时间")
  println(dateFormat.format(new Date()))
}
