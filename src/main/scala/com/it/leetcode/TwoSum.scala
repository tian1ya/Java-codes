package com.it.leetcode

import scala.collection.mutable

object TwoSum extends App {

  def index(nums: Array[Int], target: Int): Array[Int] = {
    val intToInt = new mutable.HashMap[Int, Int]()

    for (elem <- nums) {
      if (intToInt.contains(target - elem))
        return Array(nums.indexOf(elem), nums.indexOf(target - elem))
      else
        intToInt.put(elem, nums.indexOf(elem))
    }
    Array(-1, -1)
  }


  def index2(nums: Array[Int], target: Int): Array[Int] = {
    val intToInt = new mutable.HashMap[Int, Int]()
    nums.foreach(v => if (nums.contains(target - v)) intToInt.put(nums.indexOf(v), nums.indexOf(target - v)))
    Array(intToInt.head._1, intToInt.head._2)
  }

  private val ints: Array[Int] = index2(Array(1, 2, 3, 4, 5, 6, 7, 8, 9), 9)
  println(ints(0))
  println(ints(1))


}
