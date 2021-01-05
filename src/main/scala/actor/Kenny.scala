package actor

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

case object ForceRestart
class Kenny extends Actor {

  private val child: ActorRef = context.actorOf(Props[Child], name = "child")

  println("entered the kenny constructor")


  override def preStart(): Unit = {
    println("kenny preStart")
  }

  override def postStop(): Unit = {
    println("kenny postStop")
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println("kenny preRestart")
    println(s" REASON: ${reason.getMessage}")
    super.preRestart(reason, message)
  }

  override def postRestart(reason: Throwable): Unit = {
    println("kenny: postRestart")
    println(s" REASON: ${reason.getMessage}")
  }

  override def receive: Receive = {
    case ForceRestart => throw new Exception("xxx")
    case _ => println("kenny received a message")
  }
}

object LifeCycleDmo extends App {
  private val system = ActorSystem("LifeCycleDmo")

  val kenny: ActorRef = system.actorOf(Props[Kenny], name = "Kenny")

  println("sending kenny a message")

  kenny ! "hello"

  Thread.sleep(1000)

  println("kenny restart")
  kenny ! ForceRestart

  println("Kenny stop")
  system.stop(kenny)

  println("Kenny terminate")
  system.terminate()
}
