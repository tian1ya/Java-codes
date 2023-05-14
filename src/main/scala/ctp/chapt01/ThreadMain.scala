package ctp.chapt01

import ctp.chapt01.SynNest.{async, tasks, worker}

import scala.annotation.tailrec
import scala.collection.mutable

/**
 * JVM 确保同一个时间，仅允许一个线程执行同一个对象中的 synchronized 语句，如果T线程调用了 同一个对象中的 synchronized 语句
 * 那么另一个线程S在调用该语句，那么S线程就会是阻塞状态，只有当T线程执行完它语句，S线程才可以执行
 *
 * JVM 中创建的所有对象中都有一个名为【内部锁】或者【监控锁】 的特殊实体，确保同一时刻只能由一个线程执行对象中的同步代码块
 * 当线程开始执行同步代码可视化，可以将该情况描述为改线程获取了该同步代码块所在的对象的监控器的所有权，或者是该线程获取了该对象的监控器
 * 当线程执行完同步代码块，改线程就会释放同步代码块所在对象的监控器
 *
 * synchronized 使用的代码
 * 使用 synchronized 的对象，比没有使用的对象的写入操作消耗更多资源，使用它描述的同步代码块中任何变量的写入(变化)
 * 都立马会反应到其他的线程，这是有JMM内存模型机制导致
 * 【它保证变量的可见性】
 */
object ThreadMain {


  class MyThread extends Thread {
    override def run(): Unit = {
      println(s"new t run")
    }
  }

  def tRun: Unit = {
    val t1 = new MyThread
    t1.start()
    t1.join()
    // 主线程等待 t1线程执行结束，然后再执行主线程
    // 本质上，t1.join 将主线程切为 waiting 状态，然后main交出线程的控制权
    // os 将控制器交给其他线程
    // new t run 是 t1 线程输出
    // new t run end 是 main 线程输出

    println("new t run end")
  }

  def thread(body: => Unit): Thread = {
    val t = new Thread() {
      override def run(): Unit = body
    }

    t.start()
    t
  }

  var uidCount = 0L

  def getUniqueId(): Long = {
    val freshUid = uidCount + 1
    uidCount = freshUid
    freshUid
  }

  def printUidqueId(n: Int): Unit = {
    val uids = for (i <- 0 until n) yield getUniqueId()
    println(s"Generate uids: $uids")
  }

  def getUniqueIdSyc(): Long = this.synchronized {
    val freshUid = uidCount + 1
    uidCount = freshUid
    freshUid
  }

  def printUidqueIdSyc(n: Int): Unit = {
    val uids = for (i <- 0 until n) yield getUniqueIdSyc()
    println(s"Generate uids: $uids")
  }


  private val lock = new AnyRef

  def syncGuard(): Unit = {
    var message: Option[String] = Option.empty

    val greater = thread {
      lock.synchronized { // greater 获得锁
        while (message == None) lock.wait() // wait 可能被虚假唤醒，JVM偶尔会在没有notify 的情况下将wait 唤醒。
        // 所以这里增加虚假唤醒的判断处理机制,检测条件，这里称为是保卫锁
        //greater 进入阻塞，让其他线程开始抢 lock 锁
        println(message.get)
      }
    }

    lock.synchronized { // 这里主线程获取到 lock 锁
      message = Option("hello world") // 设置值
      lock.notify() // main 唤醒其他正在等待 lock 的线程，这里执行结束后 main 线程就执行完获取lock 的逻辑，并释放锁，其他线程抢锁
    }

    greater.join()
  }

  private val tasks: mutable.Queue[() => Unit] = mutable.Queue[() => Unit]()

  val worker = new Thread() {
    def poll(): Option[() => Unit] = tasks.synchronized {
      //      if (tasks.nonEmpty) Some(tasks.dequeue()) else None
      while (tasks.isEmpty) // 增加保卫锁
      {
        print("等呀等")
        tasks.wait()
      }
      Some(tasks.dequeue())
    }

    override def run(): Unit = while (true) poll() match {
      case Some(t) => t()
      case None =>
    }
  }

  /**
   * 线程终止标志
   */
  private var terminalFlag: Boolean = false


  /**
   * 不使用 interrupt 的方式终止程序
   * 改方法在线程等待状态的执行的时候会抛出异常，且异常可以获取并处理
   * 如果是在线程执行的过程中，调用线程的改方法，那么线程还会执行结束，担心线程的 isInterrupt 方法会返回 true
   */
  val workerWithTerminalFlag = new Thread() {
    def poll(): Option[() => Unit] = tasks.synchronized {
      //      if (tasks.nonEmpty) Some(tasks.dequeue()) else None
      while (tasks.isEmpty && !terminalFlag) { // 增加保卫锁 && 线程终止条件，当task 为空，以及线程没有被终止时候等待
        print("等呀等")
        tasks.wait()
      }

      // 获得了锁，且线程没有终止，然后弹出任务执行
      // 如果在线程获取到锁，但是后续被主线程给 shutDown 了，那么这里也直接返回 None，然后 run 方法会执行停止
      if (!terminalFlag) {
        Some(tasks.dequeue())
      } else None
    }

    //    override def run(): Unit = while (true) poll() match {
    //      case Some(t) => t()
    //      case None =>
    //    }
    @tailrec override def run(): Unit = poll() match {
      case Some(t) => t(); run()
      case None =>
    }

    def shutDown():Unit = tasks.synchronized {
      terminalFlag = true
      tasks.notify()
    }
  }

  def async(body: => Unit): Unit = tasks.synchronized {
    tasks.enqueue(() => body)
    tasks.notify()
  }

  /**
   *
   * volatile: 修饰的变量以原子的方式执行，并且其变量的更新会立马让所有的线程感知
   * 【经常被用作修饰状态参数，如用于标识某个操作已经执行或者执行结束】
   * 但是 synchronize 已经被JVM优化很多，大多数情况下优先使用它，volatile 意义的实现复杂，容易错处
   * 在没有其他同步机制的情况下，容易出错
   */

    // 【happen-before】
    /*
      用于描述，线程之间，能够【了解彼此执行写入内存操作】的情况， 在JVM中动作(action)种类包括 volatile 变量的【读写】、【获取】和【释放对象中的监控器】
      【运行线程】，以及【终止线程】，如果动作A 是在动作B之前执行的，那么动作B就能够了解动作A 的写入操作。
      以及其有传递性，如线程A了解线程B的动作，线程B了解线程C的动作，那么线程A也了解线程C的动作

      JVM 可以保证volatile 变量以及前后的执行顺序不变, 并实现所有的 happen-before 规则
     */
  def main(args: Array[String]): Unit = {
    //    val t: Thread = Thread.currentThread()
    //    val name: String = t.getName
    //    println(s"sss $name")

    //    tRun

    //    val thread = t2Run(println("t2Run"))
    //    thread.join()
    //    println("new t2 run end")

    /**
     * 下面的非原子执行的代码，每次执行的结果都是不一样的
     * 线程对 uidCount 有竞态条件
     *
     * 存在竞态条件并不是程序出错的必要，而是出现了不可预期的情况下
     * 也就是下面的打印会出现相同的情况
     */
    //    val t = thread(printUidqueId(5))
    //    printUidqueId(5)
    //    t.join()

    /**
     * 解决上述问题，首先可以【同步化】
     * Synchronized 语句确保了由一个线程执行的同步化代码块
     * 确保同一个对象 this 中掐同步代码块不会被调用
     * 下面加了同步语句之后，打印的结果就不会出现重复的内容
     */

    //    val tSyc = thread(printUidqueIdSyc(5))
    //    printUidqueIdSyc(5)
    //    tSyc.join()


    //    syncGuard

    worker.setName("taskQueue")
    //    worker.setDaemon(true)
    worker.start()

    Thread.sleep(1000 * 3)

    async(println("async 1"))
    async(println("async 2"))
    async(println("async 3"))
  }
}

