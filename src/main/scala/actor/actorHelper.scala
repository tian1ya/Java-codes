package actor

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.util.Timeout
import akka.pattern.ask

import scala.concurrent.{Await, Future, ExecutionContext}
import scala.concurrent.duration._
import scala.language.postfixOps

case object AskNameMessage

class HelloActor extends Actor {
  override def receive: Receive = {
    case AskNameMessage => sender ! "Fred"
    case _ => println("taht was xxx")
  }
}

object actorHelper extends App {
  implicit val timeout = Timeout(5 seconds)

  val dddActorSystem = ActorSystem("ddd")

  val myActor: ActorRef = dddActorSystem.actorOf(Props[HelloActor], name = "myActor")



//  private val res: String = Await.result(fu, timeout.duration).asInstanceOf[String]

//  println(res)
}
