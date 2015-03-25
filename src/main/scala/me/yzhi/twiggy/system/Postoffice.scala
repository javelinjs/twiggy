package me.yzhi.twiggy.system

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
 */
class Postoffice private {
  val yellowPages: YellowPages = _
  var app: AppContainer = _

  val system = ActorSystem("Postoffice")
  var receiver: ActorRef = _
  var sender: ActorRef = _

  def start(args: Array[String]) = {
    yellowPages.init()
    yellowPages.van.myNode match {
      // FIXME
      case Node.SCHEDULER =>
        app = AppContainer.create(args)
        // TODO
        assert(app != null)
      case _ =>
        // connect to the scheduler, which will send back a create_app request
        val task = new Task(opt=Task.MANAGE, request=true, time=0)
        val msg = new Message(task)
        msg.recver = yellowPages.van.scheduler.id
        this.send(msg)
    }

    receiver = system.actorOf(Props(new Receiver))
    sender = system.actorOf(Props(new Sender))
  }

  def send(msg: Message) {
    if (msg.valid && !msg.terminate) {
      val stat = yellowPages.van.send(msg, 0)
      for (s <- stat if s.ok) {
        // heartbeat_info_.increaseOutBytes(send_bytes);
        return
      }
      // TODO: log failure
    } else {
      // TODO: do not send, fake a reply mesage
    }
  }

  class Receiver extends Actor {
    // TODO
    override def receive: Receive = ???
  }

  class Sender extends Actor {
    // TODO
    override def receive: Receive = ???
  }
}

object Postoffice {
  val instance = new Postoffice()
}
