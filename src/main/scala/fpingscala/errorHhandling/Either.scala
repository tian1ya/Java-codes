package fpingscala.errorHhandling

sealed trait Either[+E, +A]
case class Left[+E](value: E) extends Either[E, Nothing] // 表示成功
case class Right[+A](value: A) extends Either[Nothing, A] // 表示失败， 二者是互斥的


object Either {

}
