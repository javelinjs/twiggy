package me.yzhi.twiggy.system

import me.yzhi.twiggy.system.Node.NodeID

/**
 */
class Executor(val obj: Customer) {
  def rnode(k: NodeID): RemoteNode = {
    // TODO:
    null
  }

  def add(node: Node): Unit = {
    // TODO
  }

  def finish(msg: Message): Unit = {
    // TODO
  }
}

package object PS {
  // all server nodes
  val kServerGroup = Van.id("all_servers")
  // all worker nodes
  val kWorkerGroup = Van.id("all_workers")
  // kServerGroup + kWorkerGroup
  val kCompGroup = Van.id("all_comp_nodes")
  // the nodes maintaining a replica of the key segment I own
  val kReplicaGroup = Van.id("all_replicas")
  // the owner nodes of the key segments this node backup
  val kOwnerGroup = Van.id("all_owners")
  // all live nodes, including scheduler, workers, servers, unused nodes...
  val kLiveGroup = Van.id("all_lives")
}
