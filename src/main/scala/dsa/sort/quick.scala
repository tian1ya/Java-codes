package dsa.sort

import scala.util.control.Breaks._

/*
    bubble insert select 没有使用递归
    quick: 是对bubble 排序的改进

    基本思想：
      通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另一部分的书友数据都小
      然后再按此方法对着两部分数据分别进行快速排序，整个排序过程
      可以递归进行，以此达到整个数据变成有序序列
 */
class quick {
  def sort(arr: Array[Int], left: Int, right: Int): Unit = {
    var l = left
    var r = right
    var middle = arr((l + r) / 2)

    var tmp = 0

    // 元素交换 比中间小的放到左边，大的放到右边
    breakable {
      while (l < r) {
        //左边找一个比中间值大的值
        while (arr(l) < middle)
          l += 1

        //右边找一个比中间值小的值
        while (arr(r) > middle)
          r -= 1

        // 说明这次此次交换都结束了, 二者穿过了对方的边界
        if (l >= r)
          break

        // 交换
        tmp = arr(l)
        arr(l) = arr(r)
        arr(r) = tmp

        // 如果交换之后l == middle，也就是右边的值都大于middle， 左边的值都小于middle，
        // 经过上面的交换，arr(l) 的值其实是 arr(r) 的是，所以这里将 r-1，前近一步，下次就不会对和middle 等于的这个值进行排序了
        // 对 arr(r)也是 相同的道理
        if (arr(l) == middle)
          // 经过上面的交换, arr(l) 的值其实是 arr(r) 那边找到的值，也就是右边的值现在均都大于middle，所以最小值找到了middle，这个时候，r = (l + r) / 2
          // 所以 r - 1，这个时候 【r, arr.length】值均都大于middle
          r -= 1
        if (arr(r) == middle)
        // 经过上面的交换, arr(r) 的值其实是 arr(l) 那边找到的值，也就是左边的值现在均都小于于middle，所以最大值找到了middle，这个时候，l = (l + r) / 2
        // 所以 l + 1，这个时候 【0, l】均都小于于middle
          l += 1
      }
    }

    // 当出现这种情况的时候，说明当前的arr，在以middle 划分左右大小的时候，左边均全部小于middle，右边均大于middle
    // 所以middle 这个排序的位置就固定了，后面不会再动了
    // 所以会对l前进1， r后退1
    if (l == r) {
      l += 1
      r -= 1
    }

    // 向左递归处理
    if (left < r)
      sort(arr, left, r)

    // 向右递归排序
    if (right > l)
      sort(arr, l, right)
  }
}

object quick extends App {
  val random = new util.Random()
//  val arr = new Array[Int](20)
//  for(i <- 0 until 20)
//    arr(i) = random.nextInt(10)

  val arr = Array(101,34,119,120, 234)
  val quick1 = new quick
  quick1.sort(arr, 0, arr.length-1)

  println(arr.mkString(", "))
}
