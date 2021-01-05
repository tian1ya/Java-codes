package dsa.sort

/*
  利用归并的思想实现的排序
  该算法使用经典的分治策略(
    分：将问题分为一些小的问题后递归求解，
    治：将分阶段得到的答案合在一起，合并的过程中进行治理(如排序，在这个阶段完成排序)

    tmp 提前开辟好，且带下和 arr 一样

  和 快排差不多，基本都是分开递归解法

 */
class merge {
  def sort(arr: Array[Int], left: Int, right: Int, tmp: Array[Int]): Unit = {
    if (left < right) {
      val mid = (left + right) / 2
      sort(arr, left, mid, tmp)
      sort(arr, mid+1, right, tmp)

      mergeRes(arr, left, mid, right, tmp)
    }
  }

  def mergeRes(arr: Array[Int], left: Int, mid: Int, right: Int, tmp: Array[Int]): Unit = {
    var i = left // 左边索引
    var j = mid + 1 // 右边第一个索引
    var t = 0 // 临时数组tmp的索引

    while (i <= mid && j <= right) {
      if (arr(i) <= arr(j)) {
        // 当前的左边的有序列表的第一个值，小于当前的右边的有序列表的第一个值
        // 当前左边的元素拷贝
        tmp(t) = arr(i)
        t += 1
        i += 1
      }else {
        // 当前的左边的有序列表的第一个值，大于当前的右边的有序列表的第一个值
        // 当前右边的元素拷贝
        tmp(t) = arr(j)
        t += 1
        j += 1
      }
    }

    // 上面拷贝结束， 如果左边数据还有剩余数据，依次拷贝到tmp 数组
    while(i <= mid) {
      tmp(t) = arr(i)
      t += 1
      i += 1
    }
    // 上面拷贝结束， 如果右边数据还有剩余数据，依次拷贝到tmp 数组
    while(j <= right) {
      tmp(t) = arr(j)
      t += 1
      j += 1
    }

    // 本次 tmp 拷贝到原数组中
    // tmp 在某个合并阶段，并不是每次都拥有待排序的所有数据
    t = 0
    var tmpLeft = left
    while (tmpLeft <= right) {
      arr(tmpLeft) = tmp(t)
      t += 1
      tmpLeft += 1
    }
  }
}

object merge extends App {
  val arr = Array(101,34,119,120, 234)
  val tmp = new Array[Int](arr.length)

  val merge = new merge
  merge.sort(arr, 0, arr.length-1, tmp)

  println(arr.mkString(", "))
}
