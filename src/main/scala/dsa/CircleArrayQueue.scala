package dsa

import scala.io.StdIn

class CircleArrayQueue(arrayMaxSize: Int) {
  val maxSize = arrayMaxSize
  val arr = new Array[Int](arrayMaxSize)
  //指向头
  var front = 0
  //指向尾
  var rear = 0;


  //队列预留一个空间作为约定
  def isFull(): Boolean = {
    /*
      整除：说明能够完整的回到起点
     */
    (rear + 1) % maxSize == front
  }

  def isEmpty(): Boolean = {
    rear == front;
  }

  def addQueue(n: Int): Unit = {
    if (isFull()) {
      println("queue full")
      return
    }

    arr(rear) = n
    rear = (rear + 1) % maxSize
  }

  /*
    这里 getQueue 相当于是Dequeue，，front 需要前进到下一位
   */
  def getQueue(): Any = {
    if (isEmpty()) {
      return new Exception("queue Empty")
    }

    val value = arr(front)
    front = (front + 1) % maxSize;
    value
  }

  def showQueue(): Unit = {
    if (isEmpty()) {
      return new Exception("queue Empty")
    }
    /*
      从front 取，取出几个元素
      注意取值的时候，总是从 rear 或者 front 为下标，而在循环更新 rear 或者 front 的时候 (rear + 1) % maxSize
     */
    for (index <- front until front + size) {
      printf("arr[%d]=%d\n", index % maxSize, arr(index % maxSize))
    }
  }

  def size(): Int = {
    (rear + maxSize - front) % maxSize
  }

  def headQueue(): Any = {
    if (isEmpty()) {
      return new Exception("queue Empty")
    }
    arr(front+1)
  }
}

object CircleArrayQueue {
  def main(args: Array[String]): Unit = {
    val queue = new CircleArrayQueue(3)

    var key = ""
    while (true) {
      println("show: 显示队列")
      println("exit: 退出队列")
      println("add: 退出程序")
      println("get: 获取 queue 首元素")
      println("head: 查看 queue 首元素")
      key = StdIn.readLine()
      key match {
        case "show" => queue.showQueue()
        case "exit" => System.exit(0);
        case "add" => {
          println("请输入一个数")
          val value = StdIn.readInt()
          queue.addQueue(value)
        }
        case "get" => {
          val font = queue.getQueue()
          if (font.isInstanceOf[Exception]) {
            println(font.asInstanceOf[Exception].getMessage)
          } else {
            println(s"取出的数据是 $font")
          }
        }
        case "head" => {
          val font = queue.headQueue()
          if (font.isInstanceOf[Exception]) {
            println(font.asInstanceOf[Exception].getMessage)
          } else {
            println(s"取出的数据是 $font")
          }
        }
      }
    }
  }
}
