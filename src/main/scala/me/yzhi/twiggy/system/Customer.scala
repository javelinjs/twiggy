package me.yzhi.twiggy.system

import me.yzhi.twiggy.system.Node.NodeID

/**
 */
class Customer(val name: String) {
  val sys = Postoffice.instance
  val exec = new Executor(this)

  def port(nodeId: NodeID): RemoteNode = {
    val rnode = exec.rnode(nodeId)
    require(rnode != null)
    rnode
  }
}
