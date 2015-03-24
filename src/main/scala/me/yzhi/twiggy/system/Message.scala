package me.yzhi.twiggy.system

import me.yzhi.twiggy.system.Node.NodeID

import scala.{specialized => spec}

/**
  */
class Message { // [@spec(Double, Int, Float, Long) T] {
  type Callback = () => Unit

  var task: Task = _

  // sender node id
  var sender: NodeID = _
  // receiver node id
  var recver: NodeID = _
  // the original receiver node id. for example, when a worker send a message to
  // the server group (kServerGroup), then the message going to a particular
  // server will have kServerGroup as its *original_recver*
  var originalRecver: NodeID = _

  // true if this message has been replied, to avoid double reply
  var replied = false
  // true if the task asscociated with this message has been finished.
  var finished = true
  // an inivalid message will not be sent, instead, the postoffice will fake a
  // reply message. see Postoffice::queue()
  var valid = true
  // set it to be true to stop the sending thread of Postoffice.
  var terminate = false
  // wait or not when submit this message
  var doWait = false

  var callback: () => Unit = _

  // *recv_handle* will be called if anythings goes back from the destination
  // node. When called, this task has not been marked as finished. If could be
  // called multiple time when the destination node is a node group.
  var recvHandle: Callback = _
  // *fin_handle* will be called when this task has been finished. If the dest
  // node is a node group, then it means replies from all nodes in this group
  // have been received.
  var finHandle: Callback = _


  def this(tk: Task) {
    this()
    task = tk
  }

  def this(dest: NodeID, time: Int, waitTime: Int) {
    this()
    // TODO
  }

  def miniCopyFrom(msg: Message) = {
    // TODO
  }
}
