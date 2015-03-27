package me.yzhi.twiggy.system

import me.yzhi.twiggy.system.ManageNode.Command

/**
 */
class ManageNode(val cmd: Command) {
}

object ManageNode {
  class Command {
  }
  case object CONNECT extends Command
  case object ADD extends Command
  case object UPDATE extends Command
  case object REPLACE extends Command
  case object REMOVE extends Command
}
