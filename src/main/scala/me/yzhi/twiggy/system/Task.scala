package me.yzhi.twiggy.system

/**
 */
class Task(val opt :TaskOpt, val request: Boolean, val time: Int) {
  var customer: String = _
  var mngApp: ManageApp = null
  var mngNode: ManageNode = null
}

sealed trait TaskOpt

object Task {
  case object TERMINATE extends TaskOpt
  case object TERMINATE_CONFIRM extends TaskOpt
  case object REPLY extends TaskOpt
  case object MANAGE extends TaskOpt
  case object CALL_CUSTOMER extends TaskOpt
  case object HEARTBEATING extends TaskOpt
}
