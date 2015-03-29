package me.yzhi.twiggy.system

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
}

class Status {
  var state = null
  def ok = { state == null }
}
