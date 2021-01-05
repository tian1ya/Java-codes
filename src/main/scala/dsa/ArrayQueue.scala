package dsa

import scala.io.StdIn

class ArrayQueue(arrayMaxSize: Int) {
  val maxSize = arrayMaxSize
  val arr = new Array[Int](arrayMaxSize)

  // 队列头,指向头数据的前一个位置
  var front = -1

  // 队列尾,指向队列的最后数据
  var rear = -1;

  def isFull(): Boolean = {
    rear == maxSize - 1
  }

  def isEmpty(): Boolean = {
    front == rear
  }

  def showQueue(): Unit = {
    if (isEmpty()) {
      println("Queue empty")
    } else {
      for (index <- front + 1 to rear) {
        printf("arr[%d]=%d\n", index, arr(index))
      }
    }
  }

  def addQueue(n: Int): Unit = {
    if (isFull()) {
      println("Queue full")
      return
    }

    // rear 先后移
    rear += 1
    arr(rear) = n;
  }

  def getQueue(): Any = {
    if (isEmpty()) {
      return new Exception("queue Empty")
    }
    front += 1
    arr(front)
  }

  def headQueue(): Any = {
    if (isEmpty()) {
      return new Exception("queue Empty")
    }
    arr(front+1)
  }
}


object ArrayQueue {
  def main(args: Array[String]): Unit = {
    val queue = new ArrayQueue(3)

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