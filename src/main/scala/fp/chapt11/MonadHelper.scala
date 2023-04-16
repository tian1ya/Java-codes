package fp.chapt11

/*
   确保返回值和参数保持一样的性质，所以定义一个Map 的接口，其定义不出太多的操作其作用并不是那么的显著，

   Functor：
    将 map 函数提升到一个上下文中，例如 Option 中有 map 方法，将 map 变化现在在 Option 上下文中
    在经过Functor 操作后，其上下文是不变的(数据结构是不变的)，输入是List，输出肯定还是 List，只不过里面的值
    经过 map 函数后发生了变化

   Monad
    关系单独的数据类型中寻找并定义最小的一个原始的操作集合，在这个之上通过组合定义更多可用的操作
    如我们定义了 unit 和 flatMap 为最小操作单元，然后可以通过二者的组合，构造出很多的操作，如 map

     Monad 抽取出了重复的代码，不是对一个类型的泛化，而是大量的不同的数据类型满足Monad 接口和法则的抽象
     允许一劳永逸的堆很多看起来没有共性的类型编写多个组合子.

     monad 首先就是一个 Functor，其继承了 Functor;associativity（结合律） and identity(单位)
       associative law for monoids: op(op(x,y), z) == op(x, op(y,z))
       associative law for monads in a much more symmetric way:
       compose(compose(f, g), h) == compose(f, compose(g, h)

       A monad is an implementation of one of the minimal sets of monadic
      combinators, satisfying the laws of associativity and identity


 */
trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]

  // or called unzip
  def distributor[A, B](fab: F[(A, B)]): (F[A], F[B]) = (map(fab)(_._1), map(fab)(_._2))

  def codistributor[A, B](e: Either[F[A], F[B]]): F[Either[A, B]] = e match {
    case Left(fa) => map(fa)(Left(_))
    case Right(fa) => map(fa)(Right(_))
  }
}

trait Monad[F[_]] extends Functor[F] {
  def unit[A](a: => A): F[A]

  def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B]

  def map[A, B](ma: F[A])(f: A => B): F[B] = flatMap(ma)(e => unit(f(e)))

  def map2[A, B, C](ma: F[A], mb: F[B])(f: (A, B) => C): F[C] = flatMap(ma)(aa => map(mb)(bb => f(aa, bb)))

  def sequence[A](lma: List[F[A]]): F[List[A]] = lma.foldRight(unit(List[A]()))((e, z) => map2(e, z)(_ :: _))

  def traverse[A, B](la: List[A])(f: A => F[B]): F[List[B]] = la.foldRight(unit(List[B]()))((e, z) => map2(f(e), z)(_ :: _))

  def replicateM[A](n:Int, ma: F[A]): F[List[A]] = sequence(List.fill(n)(ma))

  // 结合律
  def compose[A,B,C](f: A => F[B], g: B => F[C]) : A => F[C] = a => flatMap(f(a))(g)

  def join[A](mma: F[F[A]]): F[A] = flatMap(mma)(ma => ma)
}

/*
    这里使用Id 类型作为包装，然后在对 value 做完处理之后，对其进行包装为 Id 类型，可以说 monad 提供了一个引入和绑定变量的上下文
    同时执行了变量替换
 */
case class Id[A](value: A) {
  def map[B](f: A => B):Id[B] = Id(f(value))

  def flatMap[B](f: A => Id[B]): Id[B] = f(value)
}


object MonadHelper extends App {

  val listFunctor = new Functor[List] {
    override def map[A, B](as: List[A])(f: A => B): List[B] = as map f
  }

  private val ints: List[Int] = listFunctor.map(List(1, 2, 3, 4))(z => z * 2)
  println(ints.mkString(","))

  /*
     到这里总算是能明白一些 Monad 对函数的定义API 的抽象总结能力了，所有的api 均是基于 unit 和 flatMap 2个基本操作
     所以在 optionalMonad streamMonad 等均override 这2个函数
     而其他的基于这2个函数的api 均是在 Monad 的 trait 中实现， 这样在 optionalMonad streamMonad 就同时具有了这些API
     的能力。
   */
  val optionalMonad = new Monad[Option] {
    override def unit[A](a: => A): Option[A] = Some(a)

    override def flatMap[A, B](ma: Option[A])(f: A => Option[B]): Option[B] = ma flatMap f
  }

  val streamMonad = new Monad[Stream] {
    override def unit[A](a: => A): Stream[A] = Stream(a)

    override def flatMap[A, B](ma: Stream[A])(f: A => Stream[B]): Stream[B] = ma flatMap f
  }

  val listMonad = new Monad[List] {
    override def unit[A](a: => A): List[A] = List(a)

    override def flatMap[A, B](ma: List[A])(f: A => List[B]): List[B] = ma flatMap f
  }

  private val list: List[Int] = listMonad.unit(1)
  println(list.mkString(","))

  private val list1: List[Int] = listMonad.flatMap(List[List[Int]](List(1,2,3), List(4,5,6)))(a => a.map(a => a*2))
  println(list1.mkString(","))

  private val list2: List[String] = listMonad.map2[Int, String, String](List[Int](1,2,3,4), List[String]("1","2","3"))((e1, e2) => e1.toString + "->" + e2)
  println(list2.mkString(","))
//
  private val option: Option[List[Int]] = optionalMonad.sequence(List(Some(1), Some(2), Some(3)))
  println(option.mkString(","))
//
//  private val option1: Option[List[Int]] = optionalMonad.replicateM(2, Some(2))
//  println(option1.mkString(","))
//
//  private val functionaa: Int => List[Int] = listMonad.compose[Int, Int, Int](a => List(a + 1), a => List(a*3))
//  println(functionaa(2).mkString(","))
//
//  private val list3: List[Int] = listMonad.join(List(List(1,2,3), List(4,5,6)))
//  println(list3.mkString(","))
//
//  private val value: Id[String] = Id("Hello, ") flatMap(a => Id("Monad!").flatMap(b => (Id(a + b))))
//  println(value.value)
//
//  private val value1: Id[String] = for {a <- Id("Hello, "); b <- Id("Monad!")} yield a + b
//  println(value1.value)

}
