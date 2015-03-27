package me.yzhi.twiggy.system

import me.yzhi.twiggy.system.ManageApp.Command

/**
 */
class ManageApp(val cmd: Command) {
  var conf: String = _
}

object ManageApp {
  sealed class Command

  case object ADD extends Command
  case object REMOVE extends Command
  case object INIT extends Command
  case object RUN extends Command
  case object DONE extends Command
}
