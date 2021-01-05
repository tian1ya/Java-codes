package fp.chap07


import java.util.concurrent.{Executors, _}


object Par {
  /*
    一个装有结果的容器
    原生库java.lang.Thread 中的问题：线程直接对于操作系统，这是非常稀缺的资源，最好是能够创建很多的"逻辑线程"

    1. 问题： 那么真正发生的计算应该是在什么时候呢？ get ：unit
   */

  type Par[A] = ExecutorService => Future[A]

  def run[A](s: ExecutorService)(a: Par[A]): Future[A] = a(s)

  //  def unit[A](a: => A): Par[A] = (es: ExecutorService) => UnitFuture(a)
  def unit[A](a: A): Par[A] = (es: ExecutorService) => UnitFuture(a)

  //  def folk[A](a: => Par[A]): Par[A] = es => es.submit(() => a(es).get())

  //  def folk[A](a : => Par[A]): Par[A] = es => es.submit(() => a(es).get)

  def fork[A](a: => Par[A]): Par[A] =
    es => es.submit(new Callable[A] {
      def call = a(es).get
    })

  def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] = es => {
    val af = a(es)
    val bf = b(es)
    UnitFuture(f(af.get, bf.get))
  }

  def lazyUnit[A](a: => A): Par[A] = fork(unit(a))

  def asyncF[A, B](f: A => B): A => Par[B] = a => lazyUnit(f(a))

  def map[A, B](a: Par[A])(f: A => B): Par[B] = map2(a, unit())((a, _) => f(a))

  def sortPar(parList: Par[List[Int]]): Par[List[Int]] = map(parList)(_.sorted)


  def sequenceRight[A](as: List[Par[A]]): Par[List[A]] = as match {
    case Nil => unit(Nil)
    case h :: t => map2(h, fork(sequenceRight(t)))(_ :: _)
  }

  def sequenceBalanced[A](as: IndexedSeq[Par[A]]): Par[IndexedSeq[A]] = fork {
    if (as.isEmpty) unit(Vector())
    else if (as.length == 1) map(as.head)(a => Vector(a))
    else {
      val (l, r) = as.splitAt(as.length / 2)
      map2(sequenceBalanced(l), sequenceBalanced(r))(_ ++ _)
    }
  }

  def sequence[A](as: List[Par[A]]): Par[List[A]] = map(sequenceBalanced(as.toIndexedSeq))(_.toList)


  def equal[A](e: ExecutorService)(p: Par[A], p1: Par[A]): Boolean = p(e).get() == p1(e).get()

  // Notice we are blocking on the result of `cond`.
  def choice[A](cond: Par[Boolean])(p1: Par[A], p2: Par[A]): Par[A] = es => if (cond(es).get()) p1(es) else p2(es)


  case class UnitFuture[A](a: A) extends Future[A] {
    override def cancel(mayInterruptIfRunning: Boolean): Boolean = false

    override def isCancelled: Boolean = false

    override def isDone: Boolean = true

    // 这里没在使用Future 自带的 get 方法，而是直接返回给进来的get 这个常值
    override def get(): A = a

    override def get(timeout: Long, unit: TimeUnit): A = a
  }

}


import fp.chap07.Par._

object ParHelper extends App {

  def sum(ints: Seq[Int]): Int = ints.foldLeft(0)(_ + _)

  /*
      分治
      这种实现的并行化，但是不见得效率就会提升，因为并行计算带来的到的开销
      会多过剩下来的时间
   */

  //  def sumV2(ints: Seq[Int]): Int = {
  //    if (ints.size <= 1)
  //      ints.headOption.getOrElse(0)
  //    else {
  //      val (ints1, ints2) = ints.splitAt(ints.size / 2)
  //      sumV2(ints1) + sumV2(ints2)
  //    }
  //  }
  //
  //  def sumV3(ints: Seq[Int]): Int = {
  //    if (ints.size <= 1)
  //      ints.headOption.getOrElse(0)
  //    else {
  //      val (ints1, ints2) = ints.splitAt(ints.size / 2)
  //      Par.map2(Par.fork(sumV3(ints1)), Par.fork(sumV3(ints2)))(_+_)
  //    }
  //  }


  //    val executorService: ExecutorService = Executors.newFixedThreadPool(10)
  //
  //    Par.run(executorService)(executorService) => 23)

  //  def sum4(ints: IndexedSeq[Int]): Int =
  //    if (ints.length <= 1)
  //      ints.headOption.getOrElse(0)
  //    else {
  //      val (l, r) = ints.splitAt(ints.length)
  //      val pl = Par.unit(sum4(l))
  //      val pr = Par.unit(sum4(r))
  //      Par.run(pl) + Par.get(pr)
  //    }

  def sum3(ints: IndexedSeq[Int]): Par[Int] =
    if (ints.length <= 1)
      unit(ints.headOption.getOrElse(0))
    else {
      val (l, r) = ints.splitAt(ints.length / 2)
      map2(sum3(l), sum3(r))(_ + _)
    }


  def sum4(ints: IndexedSeq[Int]): Par[Int] =
    if (ints.length <= 1)
      unit(ints.headOption.getOrElse(0))
    else {
      val (l, r) = ints.splitAt(ints.length / 2)
      map2(fork(sum3(l)), fork(sum3(r)))(_ + _)
    }

  //  val value: Par[Int] = sum3(IndexedSeq(1,2,3,4,5))
  private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
  //  println("sum4: " + value(executorService).get())

  private val function: Par[Int] = es => es.submit(() => 1)

  private val value: Future[Int] = Par.run(executorService)(function)
  println(value.get())

  private val unitValue: Future[Int] = Par.unit(2)(executorService)
  println(unitValue.get)

  private val value1: Par[Int] = Par.fork(es => Par.UnitFuture(23))
  println(value1(executorService).get)

  val p: Par[Int] = es => es.submit(() => 1)
  val p2: Par[Int] = es => es.submit(() => 2)
  private val value2: Par[Int] = Par.map2(p, p2)((a, b) => a * b)
  println(value2(executorService).get())

  private val value3: Future[String] = Par.lazyUnit("lazyUnit")(executorService)
  println(value3.get())

  private val value4: Par[Int] = Par.asyncF[Int, Int](a => a * 3)(3)
  println(value4(executorService).get())

  val p3: Par[String] = es => es.submit(() => "mapString")

  private val value5: Par[String] = Par.map(p3)(a => a.toUpperCase)
  println(value5(executorService).get())

  private val value6: Par[List[Int]] = Par.sortPar(es => es.submit(() => List(1, 4, 2, 9, 2, 3, 4)))
  println(value6(executorService).get())


  val p31: Par[Int] = es => es.submit(() => 1)
  val p32: Par[Int] = es => es.submit(() => 1)
  private val bool: Boolean = Par.equal(executorService)(p31, p32)
  println("is Equal: " + bool)

  val choiceoOf: Par[Boolean] = es => es.submit(() => true)
  private val value7: Par[Int] = Par.choice(choiceoOf)(p31, p32)
  private val i: Int = value7(executorService).get()
  println(i)

  private val value8: Par[Int] = sum4(IndexedSeq(1, 2, 3, 4, 5))
  println(value8(executorService).get)
  executorService.shutdown()
}
