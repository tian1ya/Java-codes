//package with_cats.chapt02
//
//trait Semigroup[A] {
//  def combine(x: A, y: A): A
//}
//
//trait Monoid[A] extends Semigroup[A] {
//  def empty: A
//}
//
//object Monoid extends App {
//
//
//  implicit val booleanAndMonoid: Monoid[Boolean] = new Monoid[Boolean] {
//    override def empty: Boolean = true
//
//    override def combine(x: Boolean, y: Boolean): Boolean = x && y
//  }
//
//  implicit val booleanOrMonoid: Monoid[Boolean] = new Monoid[Boolean] {
//    override def empty: Boolean = false
//
//    override def combine(x: Boolean, y: Boolean): Boolean = x || y
//  }
//
//  implicit def setUnionMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
//    override def empty: Set[A] = Set.empty[A]
//
//    override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
//  }
//
//
//}
