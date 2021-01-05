package com.it.leetcode.huawei

/*
  求最大公约数
  1. 更相减损法：最小公倍数在求出最大公约数后 两个数相乘除以最大公约数就是最小公倍数
    求最大公约数，两个数中的较大值减去较小值，然后在减数、被减数、差之间选取两个最小值继续相减
    知道减数和被减数相等就得到最大公约数
 */
object test1 {

  def main(args: Array[String]): Unit = {
    println(findGreatestDivisor(14, 7))
  }

  def findGreatestDivisor(val1: Int, val2: Int): Int = {
    val leastCommonMultiple = findLeastCommonMultiple(val1, val2)
    (val1 * val2) / leastCommonMultiple
  }

  def findLeastCommonMultiple(va1: Int, va2:Int) : Int = {
    val diff = va1 - va2
    if (diff == 0)
      va1
    else if (diff < 0)
      findLeastCommonMultiple(va1, diff * -1)
    else
      findLeastCommonMultiple(va2, diff)
  }
}
