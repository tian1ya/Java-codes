package ctp.chapt01

import ctp.chapt01.ThreadMain.{tRun, thread}

import scala.collection.mutable

/**
 * synchronized 可以嵌套，同一个线程可以拥有多个对象的锁
 * 如下的例子，主线程拥有  transfers 和 account 两个对象的锁
 */
case class Account(val name: String, var money: Int)

object SynNest {
  val transfers = mutable.ArrayBuffer[String]()


  /*
      调用 add 主线程拥有  transfers 和 account 两个对象的锁
   */
  def logTransfer(name: String, n: Int): Unit = transfers.synchronized {
    transfers += s"transfer to account $name=$n"
  }

  def add(account: Account, n: Int): Unit = account.synchronized {
    account.money += n
    if (n > 10) logTransfer(account.name, n);
  }

  /*
     两个线程，同时获取两个独立的锁，然后又在等待尝试获取对方已经拥有的锁
   */
  def send(a: Account, b: Account, n: Int): Unit = a.synchronized {
    b.synchronized {
      a.money -= n
      b.money += n
    }
  }

  private val tasks: mutable.Queue[() => Unit] = mutable.Queue[() => Unit]()

  val worker = new Thread() {
    def poll(): Option[() => Unit] = tasks.synchronized {
      if (tasks.nonEmpty) Some(tasks.dequeue()) else None
    }

    override def run(): Unit = while (true) poll() match {
      case Some(t) => t()
      case None =>
    }
  }


  def async(body: => Unit): Unit = tasks.synchronized {
    tasks.enqueue(() => body)
  }

  def queueTask(): Unit = {
    worker.setName("taskQueue")
    worker.setDaemon(true)
    worker.start()

    async(println("async 1"))
    async(println("async 2"))
    async(println("async 3"))

    Thread.sleep(5000)
  }

  /**
   * 以上守护机制还是挺丑的，使用 wait notify
   * 当T线程调用对象a.wait方法(前提是T线程的有 a 的锁)，然后会释放 a 对象的锁，
   */


  def main(args: Array[String]): Unit = {
//    val janeAccount = Account("jane", 100)
//    val janeAccount2 = Account("jane1", 200)

    //    val t1 = thread(add(janeAccount, 5))
    //    val t2 = thread(add(janeAccount2, 50))
    //    val t3 = thread(add(janeAccount, 70))
    //
    //    t1.join()
    //    t2.join()
    //    t3.join()
    //
    //    println(s"---- transfer ----\n$transfers")
    //    println(s"---- janeAccount ----\n${janeAccount.toString}")
    //    println(s"---- janeAccount2 ----\n${janeAccount2.toString}")


//    val t1 = thread {
//      for (i <- 0 until 100) send(janeAccount, janeAccount2, 1)
//    }
//    val t2 = thread {
//      for (i <- 0 until 100) send(janeAccount2, janeAccount, 1)
//    }
//
//    t1.join()
//    t2.join()
//
//    println(s"janeAccount $janeAccount")
//    println(s"janeAccount2 $janeAccount2")

    queueTask()
  }
}
