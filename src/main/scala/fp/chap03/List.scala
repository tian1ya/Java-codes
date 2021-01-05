package fp.chap03

/*
  scala 中的 list 中 Cons ==> ::
  Cons(h,t) 可以写为 h::t
  所以在模式匹配中可以写为

  case Cons(x: Int, xs: List[Int]) ==>> X::XS
 */
sealed trait List[+A]

case object Nil extends List[Nothing]

case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def sum(list: List[Int]): Int = list match {
    case Nil => 0
    case Cons(x: Int, xs: List[Int]) => x + sum(xs)
  }

  def product(list: List[Int]): Int = list match {
    case Nil => 0
    case Cons(0.0, _) => 0
    case Cons(x: Int, xs: List[Int]) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = if (as.isEmpty) Nil else Cons(as.head, apply(as.tail: _*))

  def setHead[A](a: A, list: List[A]): List[A] = (a, list) match {
    case (a, Nil) => Cons(a, Nil)
    case (a, list) => Cons(a, list)
  }

  def printList[A](l: List[A]): Unit = l match {
    case Cons(a, al) => {
      print(a + " ")
      printList(al)
    }
    case Nil => println()
  }

  def delTail[A](list: List[A]): List[A] = list match {
    case Cons(_, Nil) => Nil
    case Cons(_, tail: Cons[A]) => tail
  }

  def len[A](list: List[A]): Int = len(list, 0)

  private def len[A](list: List[A], n: Int): Int = list match {
    case Cons(_, tail) => len(tail, n + 1)
    case Nil => n
  }

  def length[A](left: List[A]): Int = left match {
    // 一直从右边增加到左边，其中右边第一项是初始值z，这里设置为1，每往左边走一位置
    // 然后 + 1
    case Cons(_, tail) => foldRight(tail, 1)((_, a) => a + 1)
    case Nil => 0
  }

  def drop[A](list: List[A], n: Int): List[A] = {
    if (length(list) < n)
      throw new RuntimeException("list length must bigger or euqal than n")
    if (n <= 0) list
    else list match {
      case Cons(_, tail) => drop(tail, n - 1)
      case _ => list
    }
  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    // 删除前缀符合要求的项，而不是删除满足要求的项
    case Cons(head, tail) if f(head) => dropWhile(tail, f)
    case _ => l
  }

  // 科里化帮助类型推导
  def dropWhileC[A](as: List[A])(f: A => Boolean): List[A] = as match {
    case Cons(h, t) if f(h) => dropWhileC(t)(f)
    case _ => as
  }

  def append[A](a: List[A], b: List[A]): List[A] = a match {
    case Nil => b
    case Cons(h, t) => Cons(h, append(t, b))
  }

  // 右向左折叠
  def foldRight[A, B](l: List[A], z: B)(f: (A, B) => B): B = l match {
    case Cons(h, t) => f(h, foldRight(t, z)(f))
    case Nil => z
  }

  def foldRightByFoldLeft[A, B](list: List[A], z: B)(f: (A, B) => B): B = foldLeft(reverse(list), z)((a, b) => f(b, a))

  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(h, t) => foldLeft(t, f(z, h))(f)
  }

  def foldLeftSum(as: List[Int]): Int = foldLeft(as, 0)((a, b) => a + b)

  def foldLeftProduct(as: List[Int]): Int = foldLeft(as, 1)((a, b) => a * b)


  def reverse[A](as: List[A]): List[A] = foldLeft(as, List[A]())((acc, h) => Cons(h, acc))


  def appendByFoldLeft[A](a: List[A], b: List[A]): List[A] = foldLeft(reverse(a), b)((b, a) => Cons(a, b))

  def appendByFoldRight[A](a: List[A], b: List[A]): List[A] = foldRight(a, b)((a, b) => Cons(a, b))


  def sum1(list: List[Int]): List[Int] = foldRight(list, List[Int]())((h, l) => Cons(h + 1, l))

  def doubleListToString(list: List[Double]): List[String] = foldRight(list, List[String]())((h, l) => Cons(h.toString, l))

  def map[A, B](list: List[A])(f: A => B): List[B] = foldLeft(reverse(list), List[B]())((l, el) => Cons(f(el), l))

  def filter[A](list: List[A])(f: A => Boolean): List[A] = foldLeft(reverse(list), List[A]())((l, ele) => if (f(ele)) Cons(ele, l) else l)

  def flatMap[A](list: List[A])(f: A => List[A]): List[A] = foldLeft(reverse(list), List[A]())((l, a) => appendByFoldLeft(f(a), l))

  def filterByFlatMap[A](list: List[A])(f: A => Boolean): List[A] = flatMap(list)(a => if (f(a)) List(a) else Nil)

  def zipSum(a: List[Int], b: List[Int]): List[Int] = (a, b) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (a: Cons[Int], b: Cons[Int]) => Cons(a.head + b.head, zipSum(a.tail, b.tail))
  }

  def zipWith[A, B, C](a: List[A], b: List[B])(f: (A, B) => C): List[C] = (a, b) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (a: Cons[A], b: Cons[B]) => Cons(f(a.head, b.head), zipWith(a.tail, b.tail)(f))
  }



  def main(args: Array[String]): Unit = {
    val l = List(0, 1, 2, 3, 4, 5, 6)
    printList(l)

    val newList = setHead(10, l)
    //    printList(newList)
    val newList2 = delTail(newList)
    //    printList(newList2)

    val dropList1 = drop(l, 2)
    //    printList(dropList1)

    val dropwhile2 = dropWhile(l, (a: Int) => a < 3)
    //    printList(dropwhile2)

    // f 匿名函数就不需要在使用类型了，currying 会帮助推导出来
    val dropwhile3 = dropWhileC(l)(a => a < 3)
    //    printList(dropwhile3)

    val appendList = append(List(1, 2, 3), List(4, 5, 6))
    //    printList(appendList)

    val foldRightSum = foldRight(l, 0)((a, b) => a + b)
    val foldRightSum1 = foldRight(l, 0)(_ + _)
    //    println(foldRightSum + " " + foldRightSum1)

    val foldRightP = foldRight(l, 1)((a, b) => a * b)
    val foldRightP1 = foldRight(l, 1)(_ * _)
    //    println(foldRightP + " " + foldRightP1)

    val foldRightAppend = foldRight(List(1, 2, 3, 4), Nil: List[Int])(Cons(_, _))
    //    printList(foldRightAppend)

    val foldRightAppend2 = foldRight(List(1, 2, 3, 4), List(1, 2))(Cons(_, _))
    //    printList(foldRightAppend2)

    val value = List(1, 2, 3, 4, 4, 5, 6, 7)
    val foldRightLength = length(value)
    //    val i = len(value)
    //    println(foldRightLength == i)
    //    print(foldRightLength)

    val foldLeftTest = List(1, 2, 3, 4)
    val flS = foldLeftSum(foldLeftTest)
    //    println(flS)
    val flp = foldLeftProduct(foldLeftTest)
    //    println(flp)
    val reversse = reverse(value)
    //    printList(reversse)

    val appenddeding = appendByFoldLeft(value, List(5, 6, 7))
    val appenddedddding = appendByFoldRight(value, List(5, 6, 7))
    //    printList(appenddeding)
    //    printList(appenddedddding)

    val sumOssne = sum1(value)
    //    printList(sumOssne)

    val double2String = doubleListToString(List(1.0, 2.0, 3.0))
    //    printList(double2String)

    val mapSquare = map(List(1, 2, 3, 4))(a => a * a)
    //    printList(mapSquare)

    val filterd = filter(List(1, 2, 3, 4, 5))(a => a > 2)
    //    printList(filterd)

    val flatMasp = flatMap(List(1, 2, 3))(a => List(a, a))
    //    printList(flatMasp)

    val sumZip = zipSum(List(1, 2, 3), List(1, 1, 1))
    //    printList(sumZip)
    val zipWithSum = zipWith(List(1, 2, 3), List(1, 1, 1))((a, b) => a + b)
    printList(zipWithSum)
  }
}
