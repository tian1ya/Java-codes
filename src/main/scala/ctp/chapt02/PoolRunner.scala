package ctp.chapt02

import scala.concurrent.forkjoin.ForkJoinPool

object PoolRunner {

  def folkJoinPoolR(): Unit = {
    val executor: ForkJoinPool = new ForkJoinPool
    executor.execute(new Runnable {
      override def run(): Unit = print("aaa")
    })

    Thread.sleep(2000)
  }

  def main(args: Array[String]): Unit = {

  }

}
