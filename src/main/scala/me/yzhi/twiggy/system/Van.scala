package me.yzhi.twiggy.system

import me.yzhi.twiggy.system.Node.NodeID

/**
 */
class Van {
  // TODO
  val myNode: Node = _
  val scheduler: Node = _

  def send(msg: Message, sendBytes: Int): Option[Status] = {
    None
  }

  def connect(node: Node): Status = {
    // TODO
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
}

class Status {
  var state = null
  def ok = { state == null }
}
