package actor

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

case object PingMessage

case object PongMessage

case object StartMessage

case object StopMessage

class Ping(pong: ActorRef) extends Actor {
  var count = 0

  def incrementAndPrint: Unit = {
    count += 1
    println("ping")
  }

  override def receive: Receive = {
    case StartMessage => {
      incrementAndPrint
      pong ! PingMessage
    }

    case PongMessage => {
      incrementAndPrint
      if (count > 99) {
        sender ! StopMessage
        println("ping stoped")
        context.stop(self)
      } else {
        sender ! PingMessage
      }
    }

    case _ => println("Ping got something unexpected.")
  }
}

class Pong extends Actor {
  override def receive: Receive = {
    case PingMessage => {
      println(" pong")
      sender ! PongMessage
    }
    case StopMessage => {
      println("pong stopped")
      context.stop(self)
    }
    case _ => println("Pong got something unexpected")
  }
}


object PingPongTest extends App {
  private val system = ActorSystem("pingPongTest")
  private val pong: ActorRef = system.actorOf(Props[Pong], name = "pong")
  private val ping: ActorRef = system.actorOf(Props(new Ping(pong)), name = "ping")

  ping ! StartMessage

  system.terminate()
}