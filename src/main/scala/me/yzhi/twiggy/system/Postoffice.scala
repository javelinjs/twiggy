package me.yzhi.twiggy.system

/**
 */
class Postoffice private {
  val yellowPages: YellowPages = _
  var app: AppContainer = _

  def start(args: Array[String]) = {
    yellowPages.init()
    yellowPages.van.myNode match {
      case Node.SCHEDULER =>
        app = AppContainer.create(args)
        // TODO
        assert(app != null)
      case _ =>
        val task = new Task
        // TODO
    }
  }
}

object Postoffice {
  val instance = new Postoffice()
}
