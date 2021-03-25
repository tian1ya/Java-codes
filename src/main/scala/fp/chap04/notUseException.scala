//package fp.chap04
//
//
//sealed trait Option[+A] {
//  def map[B](f: A => B): Option[B] = this match {
//    case Some(a: A) => Some(f(a))
//    case None => None
//  }
//
//  def flatMap[B](f: A => Option[B]): Option[B] = {
//    map(f).getOrElse(None)
//
//  }
//
//  def getOrElse[B >: A](defaule: => B): B = this match {
//    case None => defaule
//    // 注意这里使用的时候，返回的是Some，Option 有时候会是Some(Some(n))
//    case Some(a) => a
//  }
//
//  def filter(f: A => Boolean): Option[A] = this match {
//    case None => None
//    case Some(a) if f(a) => this
//  }
//
//  def orElse[B >: A](ob: => Option[B]): Option[B] = this map (Some(_)) getOrElse ob
//}
//
//case class Some[+A](get: A) extends Option[A]
//
//case object None extends Option[Nothing]
//
//object notUseException {
//
//  def thread.book.main(args: Array[String]): Unit = {
//    val ints = List(1.0, 2.0, 3.0, 4.0)
//    val value = variance(ints)
//    val res = value.getOrElse(2.0)
//    println(res)
//
//    val ints2 = List(Some(1.0), Some(2.0), Some(3.0), Some(4.0))
//        val unit = sequence(ints2)
//    //    print(unit)
//
//    val ints3 = List(Some(1.0), None, Some(3.0), Some(4.0))
//    val unit2 = sequence(ints3)
//    //    print(unit2)
//
//  }
//
//  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = a flatMap (aa => b map (bb => f(aa, bb)))
//
//  def sequence[A](a: List[Option[A]]): Option[List[A]] = a match {
//    case Nil => Some(Nil)
//    case h :: t => h flatMap (hh => sequence(t) map (hh :: _))
//  }
//
//
//  def mean(xs: Seq[Double]): Option[Double] = if (xs.isEmpty) None else Some(xs.sum / xs.length)
//
//  def variance(xs: Seq[Double]): Option[Double] = mean(xs) flatMap ((avg: Double) => mean(xs.map(value => math.pow(value - avg, 2))))
//
//  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = sequence(a.map(f(_)))
//}
