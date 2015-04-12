package me.yzhi.twiggy.system

import me.yzhi.twiggy.system.Node.NodeID

/**
 */
abstract class Customer(val name: String) {
  val sys = Postoffice.instance
  val exec = new Executor(this)

  // A customer must have an unique name in order to communicate with the
  // customer with the same name but at a different machine.  It will inherent
  // its parent's (if not empty) node information, for example, the logisitic
  // regression application and its weight are parent and child,
  // respectively. They should have the same nodes information.
  //Customer(const string& my_name, const string& parent_name = "");
  //virtual ~Customer();

  // process a message received from a remote node. It will be called by
  // executor's processing thread
  def process(msg: Message): Unit
  def slice(msg: Message, krs: Vector[me.yzhi.twiggy.util.Range]): Vector[Message]

  def port(nodeId: NodeID): RemoteNode = {
    val rnode = exec.rnode(nodeId)
    require(rnode != null)
    rnode
  }

  def schedulerID: NodeID = {
    // TODO
    null
  }
}
