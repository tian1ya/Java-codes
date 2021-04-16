package dsaPlay.tree

import scala.collection.mutable.ArrayBuffer

class MinHeap[E <: Comparable[E]] {

  private val data = new ArrayBuffer[E]()

  def this(seq: Seq[E]) = {
    this()
    seq.foreach(e => data.append(e))

  }

  private def getParentIndex(childIndex: Int): Int = childIndex / 2

  def add(e: E): Unit = {
    data.append(e)
    siftUp(data.size - 1)
  }

  private def swap(indexA: Int, indexB: Int): Unit = {
    val temp = data(indexB)
    data(indexB) = data(indexA)
    data(indexA) = temp
  }

  private def siftUp(lastChildIndex: Int): Unit = {
    if (lastChildIndex > 0 && data(lastChildIndex).compareTo(data(getParentIndex(lastChildIndex))) < 0) {
      swap(lastChildIndex, getParentIndex(lastChildIndex))
      siftUp(getParentIndex(lastChildIndex))
    }
  }

  def siftDown(index: Int): Unit = {
    val leftChildIndex = index * 2 + 1
    val rightChildIndex = leftChildIndex + 1

    var nextTopIndex = leftChildIndex

    if (rightChildIndex < getSize && data(rightChildIndex).compareTo(data(leftChildIndex)) < 0) {
      // 右孩子比较小
      nextTopIndex = rightChildIndex
    }

    if (nextTopIndex < getSize && data(nextTopIndex).compareTo(data(index)) < 0) {
      swap(index, nextTopIndex)
      siftDown(nextTopIndex)
    }
  }

  private def removeTop(): Option[E] = {
    var result: Option[E] = null

    if (data.size > 1) {
      result = Some(data(0))
      data(0) = data(data.size - 1)

      data.remove(data.size - 1)

      siftDown(0)
    } else {
      result = None
    }
    result
  }

  def getSize: Int = data.size

  def remove(): Option[E] = removeTop()

  override def toString: String = "[ " + data.mkString(",") + " ]"
}


object MinHeap extends App {
  private val minHeap = new MinHeap[Integer](Seq(1, 2, 3, 4, 5, 6))

  testRemove

  def testAdd: Unit = {

    println(minHeap.toString)
    minHeap.add(0)
    println(minHeap.toString)

    minHeap.add(9)
    println(minHeap.toString)
  }

  def testRemove: Unit = {
    testAdd
    val remove = minHeap.remove
    println("removed: " + remove.get)
    println(minHeap.toString)
  }

}
