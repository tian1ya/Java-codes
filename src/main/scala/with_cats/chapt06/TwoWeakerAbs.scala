package with_cats.chapt06

import cats.Semigroupal
import cats.instances.option._

trait Semigroupal[F[_]] {
  def product[A,B](fa: F[_], fb: F[B]):F[(A,B)]

}
object TwoWeakerAbs {
  Semigroupal.tuple2(Option(3), Option(3)).ma
}
