package me.yzhi.twiggy.system

import akka.actor.ActorSystem
import me.yzhi.twiggy.system.Node.NodeID

/**
 * Van sends (receives) packages to (from) a node
 */
class Van {
  // TODO
  var myNode: Node = _
  var scheduler: Node = _
  var system = ActorSystem("TwiggyActorSystem")

  def init(): Unit = {
    scheduler = Van.parseNode(CmdOptions.scheduler)
    if (CmdOptions.myRank < 0) {
      myNode = Van.parseNode(CmdOptions.myNode)
    } else {
      myNode = assembleMyNode()
    }

    // LI << "I am [" << my_node_.ShortDebugString() << "]; pid:" << getpid();

    connect(myNode)
    connect(scheduler)
  }

  def destroy(): Unit = {
  }

  def disconnect(node: Node): Unit = {
  }

  def connect(node: Node): Status = {
    // TODO
    null
  }

  def send(msg: Message): Option[Status] = {
    None
  }

  def recv(msg: Message): Option[Status] = {
    None
  }

  // print statistic info
  def statistic(): Unit = {
  }

  def assembleMyNode(): Node = {
    null
  }
}

object Van {
  def parseNode(config: String) = {
    // FIXME
    new Node("")
  }

  def id(name: String): NodeID = {
    name
  }

  def address(node: Node): String = {
    // return node.hostname() + ":" + std::to_string(node.port());
    ""
  }
}

class Status {
  var state = null
  def ok = { state == null }
}
