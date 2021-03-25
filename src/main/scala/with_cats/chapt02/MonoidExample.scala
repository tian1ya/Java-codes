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
//    def apply[A](implicit monoid: Monoid[A]): Monoid[A] = {
//      monoid
//    }
//
//    implicit def setUnionMonoid[A]: Monoid[Set[A]] = new Monoid[Set[A]] {
//      override def empty: Set[A] = Set.empty[A]
//
//      override def combine(x: Set[A], y: Set[A]): Set[A] = x union y
//    }
//
//    private val intSetMonoid: Monoid[Set[Int]] = Monoid[Set[Int]] // Monoid[Set[Int]].apply
//    private val strSetMonoid: Monoid[Set[String]] = Monoid[Set[String]] // Monoid[Set[String]].apply
//
//    println(intSetMonoid.combine(Set(1, 2), Set(2, 3)))
//    println(strSetMonoid.combine(Set("a", "b"), Set("c", "d")))
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
//
//
//  def associativeLaw[A](x: A, y: A, z: A)(implicit m: Monoid[A]): Boolean = {
//    m.combine(x, m.combine(y, z)) == m.combine(m.combine(z, y), z)
//  }
//
//  def identityLaw[A](x: A)(implicit m: Monoid[A]): Boolean = {
//    m.combine(x, m.empty) == m.combine(m.empty, x)
//  }
//
//
//}
