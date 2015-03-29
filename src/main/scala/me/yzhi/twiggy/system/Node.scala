package me.yzhi.twiggy.system

import me.yzhi.twiggy.system.Node.{Role, NodeID}

object Node {
  type NodeID = String
  sealed class Role
  case object SERVER extends Role
  case object WORKER extends Role
  case object MANAGER extends Role
  case object SCHEDULER extends Role
  case object GROUP extends Role
  case object UNUSED extends Role
}

class Node(val id: NodeID) {
  def role: Role = {
    // TODO
    null
  }
}


