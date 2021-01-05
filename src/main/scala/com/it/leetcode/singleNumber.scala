package com.it.leetcode

import scala.collection.mutable

object singlsseNumber extends App {

  private val array = Array(1,2,3,3,2,1,5,6,6,7,7,8,9,9)

  println(findSingleNumber(array))

  def findSingleNumber(array: Array[Int]):Int = {
    val set = new mutable.HashSet[Int]()
    for (elem <- array) {
      if (!set.add(elem))
        set.remove(elem)
    }
    set.head
  }
}
