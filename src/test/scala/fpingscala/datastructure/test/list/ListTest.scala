package fpingscala.datastructure.test.list

import fpingscala.datastructures._
import org.junit.Assert._
import org.scalatest.FunSuite

class ListTest extends FunSuite {

  test("should sum a List") {
    val list: List[Int] = List(1, 2, 3, 4, 5, 6)
    val i = List.sum(list)
    assertEquals(i, 21)
  }

  test("should product a list") {
    val list: List[Double] = List(1.0, 2.0, 3.0)
    val i = List.product(list)
    assertTrue(i == 6.0)
  }

  test("should get head and tail") {

    val list: List[Double] = List(1.0, 2.0)
    val head: Double = List.head(list)
    val tails: List[Double] = List.tail(list)
    assertTrue(head == 1.0)
    assertTrue(tails == Cons(2.0, Nil))
  }

  test("should set a new Head") {
    val list: List[Double] = List(1.0, 2.0)
    val value: List[Double] = List.setHead(list, 10.0)

    val headValue = List.head(value)
    assertTrue(headValue == 10.0)
  }

  test("should drop first n eles") {
    val list: List[Double] = List(1.0, 2.0, 3.0, 4.0)
    val tails: List[Double] = List.drop(list, 2)

    assertTrue(tails == Cons(3.0, Cons(4.0, Nil)))
  }

  test("should drop by condition") {
    val list: List[Int] = List(1, 2, 3, 4)
    val tails: List[Int] = List.dropWhile(list, (a: Int) => a >= 3)
    assertTrue(tails == Cons(1, Cons(3, Nil)))
  }

  test("should append 2 list") {
    val list: List[Int] = List(1, 2, 3, 4)

    val list2: List[Int] = List(111, 22, 33)

    val value: List[Int] = List.append(list, list2)

    assertTrue(value == Cons(1, Cons(2, Cons(3, Cons(4, list2)))))
  }

  test("should multiply by 3 use foldRight") {
    val list2: List[Int] = List(111, 22, 33)

    val value: List[Int] = List.foldRight(list2, Nil: List[Int])((h, t) => Cons(h * 3, t))

    assertTrue(value == Cons(333, Cons(66, Cons(99, Nil))))
  }

  test("should sum use foldRight") {
    val list: List[Int] = List(1, 2, 3, 4)

    val sum: Int = List.foldRight(list, 0)((a, b) => a + b)

    assertTrue(sum == 10)
  }

  test("should product use foldRight") {
    val list: List[Int] = List(1, 2, 3, 4)

    val sum: Int = List.foldRight(list, 1)((a, b) => a * b)

    assertTrue(sum == 24)
  }

  test("should get list length use foldRight") {
    val list: List[Int] = List(1, 2, 3, 4)

    val length: Int = List.foldRight(list, 0)((_, acc) => acc + 1)

    assertTrue(length == 4)
  }
}
