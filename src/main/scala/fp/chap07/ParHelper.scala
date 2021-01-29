package fp.chap07


import java.util.concurrent.{Executors, _}


object Par {
  /*
    一个装有结果的容器
    原生库java.lang.Thread 中的问题：线程直接对于操作系统，这是非常稀缺的资源，最好是能够创建很多的"逻辑线程"

    1. 问题： 那么真正发生的计算应该是在什么时候呢？ get ：unit

    type：相当于声明一个类型别名：
      type Par[A] 不是实际的数据类型，只是 ExecutorService => Future[A] 函数的类型别名

   */

  // 在线程池中 run 一个 Future
  type Par[A] = ExecutorService => Future[A]

  def run[A](s: ExecutorService)(a: Par[A]): Future[A] = a(s)

  /*
      unit is represented as a function that returns a `UnitFuture`,
      which is a simple implementation of `Future` that just wraps a constant value.
      It doesn't use the `ExecutorService` at all. It's always done and can't be cancelled.
      Its `get` method simply returns the value that we gave it.
    */
  def unit[A](a: A): Par[A] = (es: ExecutorService) => UnitFuture(a)

  //  def folk[A](a: => Par[A]): Par[A] = es => es.submit(() => a(es).get())

  //  def folk[A](a : => Par[A]): Par[A] = es => es.submit(() => a(es).get)

  def fork[A](a: => Par[A]): Par[A] = es => es.submit(new Callable[A] {
    def call = a(es).get
  })

  /*
      val a = map2(sum3(l), sum3(r))(_ + _) 这里仅仅是完成了计算图的创建，并没有触发计算
      如果一带 a(es) 那么就开始计算，得到结果 val aa = UnitFuture(f(af.get, bf.get))，
      然后在 aa.get 就能获得最后的结果

      注意这个时候，所有的计算都还是在一个线程中进行的，可以理解这里完成的步骤是 Stream 的方式，所有的计算全部建立，然后在触发一系列实际计算
   */
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
        这种实现的并行化，但是不见得效率就会提升，因为并行计算带来的到的开销会多过剩下来的时间
   */

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

  val executorService: ExecutorService = Executors.newSingleThreadExecutor()

  val value: Par[Int] = sum3(IndexedSeq(1, 2, 3, 4))
  val value14: Par[Int] = sum4(IndexedSeq(1, 2, 3, 4))
  /*
    1). map2(
            sum(indexedSeq(1,2)), sum(indexedSeq(3,4))
        )(_+_) ;

    2). map2(
            map2(unit(1), unit(2))(_+_), sum(indexedSeq(3,4))
        )(_+_)

    3). val value: Par[Int] = map2(
                                    map2(
                                        unit(1),
                                        unit(2)
                                      )(_+_),

                                    map2(
                                          unit(3),
                                          unit(4)
                                        )(_+_)
                                 )(_+_)

    4). value1: Future[Int] = value(executorService) 开始执行  map2 右边函数块，也就是第一个参数 a
            map2(
                unit(1),
                unit(2)
              )(_+_)(es),
            是一个 par，先执行这个，相当于是 map2函数体中的 val af = a(es) 部分，然后在进入到下一层的 map2 中
            这个时候形参 a = unit(1), b = unit(2), 经过计算  a(es) = UnitFuture(1),  b(es) = UnitFuture(2)
            然后到达  UnitFuture(f(af.get, bf.get)) = UnitFuture(f(UnitFuture(1).get, UnitFuture(2).get)) = UnitFuture(f(1，2)) = UnitFuture(3)

       同样右边也完成了
            map2(
                unit(3),
                unit(4)
              )(_+_)(es)
            得到结果 UnitFuture(5)
       现在变成了：map2(=>UnitFuture(3), =>UnitFuture(5))(_+_)(es)



   */
  val value1: Future[Int] = value(executorService)
  println("sum3: " + value1.get())
  val value143: Future[Int] = value14(executorService)
  println("value14: " + value143.get)

  val function: Par[Int] = es => es.submit(() => 1)

  val value3: Future[Int] = Par.run(executorService)(function)
  println(value3.get())

    val unitValue: Future[Int] = Par.unit(2)(executorService)
    println(unitValue.get)

    val value4: Par[Int] = Par.fork(es => Par.UnitFuture(23))
    println(value4(executorService).get)
  //
    val p: Par[Int] = es => es.submit(() => 1)
    val p2: Par[Int] = es => es.submit(() => 2)
    val value2: Par[Int] = Par.map2(p, p2)((a, b) => a * b)
    println(value2(executorService).get())

    val value35: Future[String] = Par.lazyUnit("lazyUnit")(executorService)
    println(value35.get())

    val value6: Par[Int] = Par.asyncF[Int, Int](a => a * 3)(3)
    println(value6(executorService).get())

    val p3: Par[String] = es => es.submit(() => "mapString")

    val value5: Par[String] = Par.map(p3)(a => a.toUpperCase)
    println(value5(executorService).get())

    val value36: Par[List[Int]] = Par.sortPar(es => es.submit(() => List(1, 4, 2, 9, 2, 3, 4)))
    println(value36(executorService).get())


    val p31: Par[Int] = es => es.submit(() => 1)
    val p32: Par[Int] = es => es.submit(() => 1)
    val bool: Boolean = Par.equal(executorService)(p31, p32)
    println("is Equal: " + bool)

    val choiceoOf: Par[Boolean] = es => es.submit(() => true)
    val value7: Par[Int] = Par.choice(choiceoOf)(p31, p32)
    val i: Int = value7(executorService).get()
    println(i)

    val value8: Par[Int] = sum4(IndexedSeq(1, 2, 3, 4, 5))
    println(value8(executorService).get)

  executorService.shutdown()
}
