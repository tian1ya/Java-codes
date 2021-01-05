package dsa.recursive

/*
  1. 创建二维数组
  2. 元素值为1 表示是墙， 0表示没有走，2 表示可以走，3 走过了但是是一条死路
  3. 确定一个走的策略，下 -> 右 -> 上 -> 左

  4. 递归回溯解决问题

  =============================
  修改策略
  上 -> 右 -> 下 -> 左

  不同的策略，会找出不同的路径

 */
class miGong {

  def buildMap(): Array[Array[Int]] = {
    // 做一个地图
    val map = Array.ofDim[Int](8, 7)
    //上下，全部置 1
    for (i <- 0 until 7) {
      map(0)(i) = 1
      map(7)(i) = 1
    }

    //左右，全部置 1
    for (i <- 1 until 7) {
      map(i)(0) = 1
      map(i)(6) = 1
    }

    // 设置挡板, 墙
    map(3)(1) = 1
    map(3)(2) = 1

    // 继续设置，为回溯， 注意在走路的时候，回溯
//    map(1)(2) = 1
//    map(2)(2) = 1

    map
  }

  def printAA(map: Array[Array[Int]]): Unit = {
    //打印地图
    for (elem <- map) {
      for (elem <- elem) {
        print(elem + " ")
      }
      println()
    }
  }


  def setWay(map: Array[Array[Int]], i: Int, j: Int): Boolean = {
    // i, j 地图Map 哪里开始找
    if (map(6)(5) == 2) {
      true
    } else {
      if (map(i)(j) == 0) {
        // 0 表示还没有走， 从这里开始递归回溯
        map(i)(j) = 2
        // 表示走过了，i,j 这个点可以走同的，但是不一定
        // 开始按照下 -> 右 -> 上 -> 左走
        if (setWay(map, i - 1, j)) {
          //1. 下走 setWay(map, i + 1, j)
          //2. 下走 setWay(map, i - 1, j)
          true
        } else if (setWay(map, i, j + 1)) { // 右走
          true
        } else if (setWay(map, i + 1, j)) {
          // 上走 setWay(map, i - 1, j)
          // 下走 setWay(map, i + 1, j)
          true
        } else if (setWay(map, i, j - 1)) {
          true
        } else {
          map(i)(j) = 3
          false
        }
      } else { // map(i)(j) 不等于0，可能为1， 2， 3
        false
      }
    }
  }
}

object miGong extends App {

  val gong = new miGong
  val map: Array[Array[Int]] = gong.buildMap()
  gong.printAA(map)
  println("==================")
  gong.setWay(map, 1, 1)
  gong.printAA(map)

}
