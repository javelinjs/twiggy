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
