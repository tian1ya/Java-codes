package fp.chap02

object currying extends App {
  def curry[A,B,C](f: (A, B) => C): A => B => C = a => b => f(a,b)

  val f = curry((a:Int, b: Int) => a + b)

  def uncurry[A,B,C](f: A => B => C): (A,B) => C = (a,b) => f(a)(b)

  val unf: (Int, Int) => Int = uncurry((a: Int) => (b: Int) => a + b)

  private val intToInt1: Int => Int = f(3)
  private val i: Int = intToInt1(4)
  println(i)

  def compose1[A,B,C](f: B => C, g: A => B): A => C = a => f(g(a))
  def compose2[A,B,C](f: B => C, g: A => B): A => C = g andThen  f

  val intToInt: Int => Int = compose1((a:Int) => a*3, (b:Int) => b *b)
  val intToInt2: Int => Int = compose2((a:Int) => a*3, (b:Int) => b *b)

  println(intToInt(3))
  println(intToInt2(2))

  println("===========")

  def funcc: (Int,Int,Int) => Int = (a,b,c) => a + b

  val curried: Int => Int => Int => Int = funcc.curried

  private val i2: Int = funcc(1,2,3)
  val i1: Int = curried(1)(2)(3)

  println(i2)
  println(i1)

}
