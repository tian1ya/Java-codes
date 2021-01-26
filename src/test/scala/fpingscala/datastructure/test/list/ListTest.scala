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

  test("should sum product adn length use foldLeft") {
    /*
        注意 foldLeft 和 foldRight 的区别
        二者都是从初始值开始卓元素去做操作， 做什么操作取决于传进去的参数

        foldLeft： f(e4, f(e3, f(e2, f(e1, z))))  ： 可以尾递归优化
        foldRight: f(e1, f(e2, f(e3, f(fe4, z))))： 不能尾递归优化
     */
    val list: List[Int] = List(1, 2, 3, 4)
    val sum: Int = List.foldLeft(list, 0)((h, t) => h + t)
    val prud: Int = List.foldLeft(list, 1)((h, t) => h * t)
    val length: Int = List.foldLeft(list, 0)((acc, _) => acc + 1)

    assertTrue(sum == 10)
    assertTrue(prud == 24)
    assertTrue(length == 4)
  }

  test("should reverse list") {
    val list: List[Int] = List(1, 2, 3, 4)

    val revsesedList: List[Int] = List.foldLeft(list, Nil: List[Int])((z, e) => Cons(e, z))
    val revsesedListV2: List[Int] = List.reverse(list)

    assertTrue(revsesedList == revsesedListV2)
  }

  test("should append 2 lists") {
    val list: List[Int] = List(1, 2, 3, 4)
    val list2: List[Int] = List(5,6,7)

    val reversedList = List.foldLeft(list, Nil:List[Int])((z, e) => Cons(e, z))
    val appendRes = List.foldLeft(reversedList, list2)((z, e) => Cons(e, z))
    print(appendRes)
  }

  test("should concat list of list") {
    val lists: List[List[Int]] = List(List(1,2,3), List(4,5,6), List(6,7,8))

    val appendRestList = List.foldRight(lists, Nil: List[Int])((e, z) => List.foldRight(e, z)((e, z) => Cons(e, z)))

    print("A")
  }

  test("should plus one by foldRight/foldLeft") {
    val list: List[Int] = List(1, 2, 3, 4)

    val plus1ByFoldRight = List.foldRight(list, Nil:List[Int])((e, z) => Cons(e + 1, z))
    val plus1ByFoldLeft = List.foldLeft(list, Nil:List[Int])((z, e) => Cons(e + 1, z))

    print("a")
  }

  test("should map function by foldRight/foldLeft") {
    val list: List[Int] = List(1, 2, 3, 4)

    val f: Int => Int = a => a + 1

    val value = List.foldRight(list, Nil: List[Int])((e, z) => Cons(f(e), z))
    print(value)
  }

  test("should filter function by foldRight/foldLeft") {
    val list: List[Int] = List(1, 2, 3, 4)

    val filter: Int => Boolean = a => a > 2

    val value = List.foldRight(list, Nil:List[Int])((e, z) => if (filter(e)) z else Cons(e, z))

    print("")

  }
}
