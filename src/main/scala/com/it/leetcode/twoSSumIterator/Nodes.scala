package com.it.leetcode.twoSSumIterator

import scala.collection.mutable.ArrayBuffer


case class Node(value: Int, var next: Node = null)

class Nodes {
  var first: Node = null
  var length: Int = 0

  def findLastNode(): Node = {
    var temNode = first
    while (temNode.next != null)
      temNode = temNode.next
    temNode
  }

  def add(value: Int): Nodes = {
    val newNode = Node(value)
    if (first == null) {
      first = newNode
    } else {
      var lastNode = findLastNode()
      lastNode.next = newNode
    }
    length += 1
    this
  }

  def pop(): Node = {
    val tempFirst = first
    first = tempFirst.next
    length -= 1
    tempFirst
  }

  def sum(other: Nodes): ArrayBuffer[Int] = {
    var flag: Int = 0
    val result = new ArrayBuffer[Int];

    if (length != other.length){
      ArrayBuffer(0)
    } else {
      var tempFirst = first
      while (tempFirst != null) {
        val leftValue = pop().value
        val rightValue = other.pop().value
        if (leftValue + rightValue >= 10) {
          result.append(leftValue + rightValue - 10)
          flag = 1
        } else {
          result.append(leftValue + rightValue + flag)
          flag = 0
        }
        tempFirst = tempFirst.next
      }
      result.reverse
    }
  }


}

