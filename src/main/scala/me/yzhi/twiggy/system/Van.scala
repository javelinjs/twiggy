package me.yzhi.twiggy.system

import me.yzhi.twiggy.system.Node.NodeID

/**
 * Van sends (receives) packages to (from) a node
 */
class Van {
  // TODO
  val myNode: Node = _
  val scheduler: Node = _

  def init(): Unit = {
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
