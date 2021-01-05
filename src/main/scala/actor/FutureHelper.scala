package actor

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Random, Success}

/*
  这个import 会引入，默认的全局执行上下文，可以将执行上下文看成线程池，并且这是访问线程池的一个简单方式
 */
import scala.concurrent.ExecutionContext.Implicits.global
class FutureHelper {

}

object Test extends App {
//  private val baseTime: Long = System.currentTimeMillis()

  def run(i: Int): Future[Int] = Future {
//    Thread.sleep(Random.nextInt(500))
    val res = i + 10
    println(s"return result from cloud: $res")
    res
  }


  val r1: Future[Int] = run(1)
  val r2: Future[Int] = run(2)
  val r3: Future[Int] = run(3)

  /*
    for推导用来把结果连接在一起。当所有3个future返回时，它们的值会被赋给变量r1, r2和r3。并且这3个值的和会从yield表达式返回，然后被赋给结果变量。
    for 推导返回一个 Future

    一个Future 就是一个并发运行计算的容器，在未来的某个时间可能会返回一个T 类型的结果或者异常
    算法的计算在Future 被创建后在某个不确定的时间开始，通过执行上下文运行在与它绑定的线程上(不是主线程)
    当Future 完成时候计算结果才可使用
    当返回一个结果时候，Future 就完成了使命，可能要么成功返回，要么失败

    一个 ExecutionContext 执行它得到的任务，可以把它看成一个线程池

    列子中的 ExecutionContext.Implicits.global 语句引入了默认的全局执行上下文对象
   */
  val result = for {
    rr1 <- r1
    rr2 <- r2
    rr3 <- r3
  } yield (rr1, rr2, rr3)

  result onComplete {
    case Success(res) => println("result: " + res)
    case Failure(ex) => ex.printStackTrace()
  }

  println("before sleep at the  end")
  Thread.sleep(2000)
}