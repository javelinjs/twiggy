package me.yzhi.twiggy.system

import me.yzhi.twiggy.system.ManageNode.Command

import scala.collection.immutable

/**
 */
class ManageNode(val cmd: Command) {
  var nodes = immutable.Vector.empty[Node]
}

object ManageNode {
  sealed class Command
  case object CONNECT extends Command
  case object ADD extends Command
  case object UPDATE extends Command
  case object REPLACE extends Command
  case object REMOVE extends Command
}
