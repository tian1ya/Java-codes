package fpingscala.datastructure.test.list

import org.junit.Assert._
import org.junit._

import org.scalatest.FunSuite


import fpingscala.datastructures._

class ListTest extends FunSuite {

  test("should create a List use apply") {
    val list: List[Int] = List(1,2,3,4,5,6)
    List.sum(list)
    println("a")
  }

}
