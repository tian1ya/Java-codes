package dsa.find

import dsa.sort.quick

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks._

class search {
  /*
    1. 找到中间值， 中间值和查找值比较
      1. == 找到了
      2. midV > 查找值，递归向左(小到大排序)/右找(大到小排序)
      3. midV < 查找值，递归向左(大到小排序)/右找(小到大排序)
   */
  // 存在返回对于下标，否则返回-1
  def binarySearch(arr: Array[Int], left: Int, right: Int, findValue: Int): Int = {
    // 默认按照 小 -> 大排序
    if (left > right)
      -1
    else {
      val midIndex = (left + right) / 2
      val midValue = arr(midIndex)
      if (midValue == findValue)
        midIndex
      else if (midValue < findValue) {
        binarySearch(arr, midIndex + 1, right, findValue)
      } else {
        binarySearch(arr, left, midIndex - 1, findValue)
      }
    }
  }


  // 如果存在多个相同的元素，那么返回这个元素的所有下标
  // 当找到一个元素之后，向左边及右边扫描，
  // 找到结果后加入到 ArrayBuffer
  def binarySearch1(arr: Array[Int], left: Int, right: Int, findValue: Int): ArrayBuffer[Int] = {

    if (left > right)
      ArrayBuffer.empty
    else {
      val midIndex = (left + right) / 2
      val midValue = arr(midIndex)
      if (midValue == findValue) {
        val arrBuffer = ArrayBuffer[Int]()
        arrBuffer.append(midIndex)

        // 左边扫描
        var tmp = midIndex - 1
        breakable {
          while (true) {
            if (tmp < 0)
              break()
            if (arr(tmp) == findValue)
              arrBuffer.append(tmp)
            tmp -= 1
          }
        }


        // 右边扫描
        tmp = midIndex + 1
        breakable {
          while (true) {
            if (tmp > arr.length - 1)
              break()
            if (arr(tmp) == findValue)
              arrBuffer.append(tmp)
            tmp += 1
          }
        }

        arrBuffer
      } else if (midValue < findValue) {
        binarySearch1(arr, midIndex + 1, right, findValue)
      } else {
        binarySearch1(arr, left, midIndex - 1, findValue)
      }
    }
  }
}

object search extends App {

  val search1 = new search
  val quick1 = new quick


  val arr = Array(1, 8, 8, 9, 10, 100, 101, 102,100, 1234)
  //  quick1.sort(arr, 0, arr.length-1)

  // 重复数组找到第一个
  //  val i: Int = search1.binarySearch(arr, 0, arr.length-1, 100)
  //  println("res: " + i)

  val arrB: ArrayBuffer[Int] = search1.binarySearch1(arr, 0, arr.length - 1, 100)
  if (arrB.size > 0) {
    println(arrB.mkString(", "))
  } else
    println("没找到")
}
