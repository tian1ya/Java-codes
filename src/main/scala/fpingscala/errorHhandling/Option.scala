package fpingscala.errorHhandling

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] = this match {
    case Some(a) => Some(f(a))
    case _ => None
  }

  def getOrElse[B >: A](default: => B): B = this match {
    case Some(a) => a
    case _ => default
  }

//  def flatMap[B](f: A => Option[B]): Option[B] = map(f) getOrElse None
  def flatMap[B](f: A => Option[B]): Option[B] = this match {
    case Some(a) => f(a)
    case _ => None
  }

  def orElse[B >: A](ob: => Option[B]): Option[B] = this match {
    case None => ob
    case _ => this
  }

  def filter(f: A => Boolean): Option[A] = this match {
    case Some(a) if f(a) => this
    case _ => None
  }
}

case class Some[+A](get: A) extends Option[A]

case object None extends Option[Nothing]

object Option {

  def variance(xs: Seq[Double]): Option[Double] = {
    if (xs.isEmpty) None
    else {
      val mean = xs.sum / xs.length
      Some(xs.map(x => math.pow(x - mean, 2)).sum / xs.length)
    }
  }

  def map2[A,B,C](a: Option[A], b: Option[B]) (f: (A,B) => C): Option[C] = a flatMap(aa => b map(bb => f(aa, bb)))

  def sequence[A](a: List[Option[A]]): Option[List[A]] = a match {
    case Nil => Some(Nil)
    case h :: t => h flatMap( hh => sequence(t) map(a => hh :: a))
  }

  def Try[A](a: => A): Option[A] = try Some(a) catch {
    case e: Exception => None
  }

  def parseInts(a: List[String]): Option[List[Int]] = sequence(a map(i => Try(i.toInt)))
  // 这里会高效，因为会对列表进行2次遍历，一次遍历 String => Int, 第二次遍历 List[Option] => Option[List]

  def traverse[A,B](a: List[A])(f: A => Option[B]): Option[List[B]] = a match {
    case Nil => Some(Nil)
    case h :: t => f(h).flatMap(aa => traverse(t)(f).map(tt => aa :: tt))
  }
}
