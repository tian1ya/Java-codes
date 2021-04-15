package dsaPlay.tree

import scala.collection.mutable.ArrayBuffer

class MinHeap[E <: Comparable[E]] {

  private val data = new ArrayBuffer[E]()

  def this(seq: Seq[E]) = {
    this()
    seq.foreach(e => data.append(e))

  }

  private def getFamilyIndex(parentIndex: Int): (Int, Int, Int) = (parentIndex, parentIndex * 2+1, parentIndex * 2 + 2)



  private def siftDown(index: Int): Unit = {
    // 最小堆，大元素下沉
    val (parentIndex, leftChildIndex, rightChildIndex) = getFamilyIndex(index)

  }

}


object MinHeap extends App {

}
