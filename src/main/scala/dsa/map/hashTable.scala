package dsa.map

import scala.util.control.Breaks._

class Emp(eId: Int, eName: String) {
  val id = eId
  var name = eName
  var next: Emp = null
}

class EmpLinkedList {

  // 定义头指针，这里head 我们之间指向一个雇员
  // 空头结点，因为要按照排序方式插入，需要比较第一个，所以这里要有一个空头节点
  var head: Emp = new Emp(-1, "")

  def find(id: Int): Emp = {
    if (head == null)
      throw new Exception("empty list")
    var tm = head
    while (tm != null && tm.id != id) {
      tm = tm.next
    }
    if (tm == null)
      throw new Exception(s"${id} not exist")
    tm
  }

  // 直接在尾部加入
  // 雇员添加的id 是自增长的
  def addToTail(emp: Emp): Emp = {
    var tail = head
    while (tail.next != null)
      tail = tail.next
    tail.next = emp
    emp
  }

  def addByIdSort(emp: Emp): Emp = {

    var tail = head
    breakable {
      while (tail != null) {
        if (tail.next == null) {
          tail.next = emp
          break()
        } else if (emp.id < tail.next.id) {
          val tmp = tail.next
          tail.next = emp
          emp.next = tmp
          break()
        } else if (tail.next.id == emp.id) {
          throw new Exception("id " + emp.id + " is already exist")
        }
        tail = tail.next
      }
    }
    emp
  }

  def list(): Unit = {
    if (head.next == null)
      throw new Exception("empty list")
    var tmp = head.next
    while (tmp != null) {
      printf("Emp(id=%d name=%s) ", tmp.id, tmp.name)
      tmp = tmp.next
    }
    println()
  }
}

class HashTable11(val size: Int) {
  val empLinkedList: Array[EmpLinkedList] = new Array[EmpLinkedList](size)

  def add(emp: Emp): Emp = {
    val key = hashFunc(emp.id)
    if (empLinkedList(key) == null) {
      val list1 = new EmpLinkedList
      list1.addToTail(emp)
      empLinkedList(key) = list1
      emp
    } else {
      val linkedList = empLinkedList(key)
      linkedList.addToTail(emp)
    }
  }

  def addByIdSort(emp: Emp): Emp = {
    val key = hashFunc(emp.id)
    if (empLinkedList(key) == null) {
      val list1 = new EmpLinkedList
      list1.addByIdSort(emp)
      empLinkedList(key) = list1
      emp
    } else {
      val linkedList = empLinkedList(key)
      linkedList.addByIdSort(emp)
    }
  }

  def list(id: Int): Unit = {
    val key = hashFunc(id)
    val linkedList = empLinkedList(key)
    if (linkedList == null)
      println(s"${id} is empty")
    else
      linkedList.list()
  }

  def listAll(): Unit = {
    for (elem <- empLinkedList) {
      elem.list()
    }
  }

  def find(id: Int): Emp = {
    val hashKey = hashFunc(id)
    val list1 = empLinkedList(hashKey)
    if (list1 != null)
      list1.find(id)
    else
      throw new Exception(s"${id} not exist")
  }

  def hashFunc(id: Int): Int = {
    id % size
  }
}

object hashTable extends App {

  val table = new HashTable11(2)
  table.addByIdSort(new Emp(2, "2"))
  table.addByIdSort(new Emp(4, "4"))
  table.addByIdSort(new Emp(5, "5"))
  table.addByIdSort(new Emp(3, "3"))
  table.addByIdSort(new Emp(6, "6"))
  table.addByIdSort(new Emp(1, "1"))

  table.listAll()
  println()
  table.list(1)

  val emp: Emp = table.find(6)
  printf("Emp(id=%d, name=%s)", emp.id, emp.name)

}
