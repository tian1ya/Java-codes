package actor

import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, PoisonPill, Props}

case class CreateChild(name: String)
case class Name(name: String)

class Child extends Actor {
  var name = "No name"


  override def postStop(): Unit = {
    println(s"D's oh! they kill me ${name}: ${self.path}")
  }

  override def receive: Receive = {
    case Name(name) => this.name = name
    case _ => println(s"Child $name got message")
  }
}

class Parent extends Actor {
  override def receive: Receive = {
    case CreateChild(name) => {
      println("create a child actor")
      val child = context.actorOf(Props[Child], name = s"$name")
      child ! Name(name)
    }
    case _ => println("Parent got some other message.")
  }
}

object ParentAndChildDome extends App {
  private val parentAndChildDome = ActorSystem("ParentAndChildDome")
  private val parent: ActorRef = parentAndChildDome.actorOf(Props[Parent], name = "parent")

  parent ! CreateChild("Tom")
  parent ! CreateChild("Jerry")

  Thread.sleep(500)

  println("look Tom")
  private val tmo: ActorSelection = parentAndChildDome.actorSelection("/ParentAndChildDome/user/parent/Tom")
  /*
    发送一个 PoisonPill 消息停止一个actor，该消息会在被处理时候停止 actor，这个消息会像平常的消息一样在邮箱中排队，
   */
  tmo ! PoisonPill

  parentAndChildDome.terminate()
}
