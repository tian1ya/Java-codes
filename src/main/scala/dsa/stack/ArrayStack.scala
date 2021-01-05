package dsa.stack
import scala.reflect.ClassTag

class ArrayStack[E: ClassTag](size: Int) {
  val maxSize: Int = size
  var stack = new Array[E](maxSize)

  var top: Int = -1

  def isFull(): Boolean = {
    top == maxSize -1
  }

  def isEmpty(): Boolean = {
    top == -1
  }

  def getTopValue(): E = {
    if (isEmpty()) {
      throw new Exception("栈空")
    }
    stack(top)
  }

  //入栈
  def push(ele: E): E = {
    if (isFull()){
      throw new Exception("栈满")
    }

    top += 1
    stack(top) = ele
    ele
  }

  //出栈

  def pop(): E = {
    if (isEmpty()) {
      throw new Exception("栈空")
    }

    val topValue = stack(top)
    top -= 1
    topValue
  }
  //遍历

  def list(): Unit = {
    if (isEmpty()) {
      throw new Exception("栈空")
    }

    for (i <- 0 to top reverse)
      print(stack(i) + "\n")
  }
}

object ArrayStack extends App {
   val stack = new ArrayStack[Int](12)
  stack.push(1)
  stack.push(2)
  stack.push(3)
  stack.push(4)

  stack.list()

  val i: Int = stack.pop()
  println(i)

  println("=============")
  stack.list()
}
