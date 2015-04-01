package me.yzhi.twiggy.system

/**
 */
class RemoteNode {
  def submit(task: Task): Int = {
    // TODO
    0
  }

  def submit(msg: Message): Int = {
    // TODO
    0
  }

  def submitAndWait(task: Task, recvHandle: Message.Callback) = {
    val msg = new Message(task)
    if (recvHandle != null) msg.recvHandle = recvHandle
    msg.doWait = true
    submit(msg)
  }

  def waitOutgoingTask(time: Int) = {
    // TODO
  }
}
