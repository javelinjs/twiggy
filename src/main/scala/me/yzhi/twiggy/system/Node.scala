package me.yzhi.twiggy.system

import me.yzhi.twiggy.system.Node.NodeID

object Node {
  type NodeID = String

  case object SERVER
  case object WORKER
  case object MANAGER
  case object SCHEDULER
  case object GROUP
  case object UNUSED
}

class Node(val id: NodeID) {
}


