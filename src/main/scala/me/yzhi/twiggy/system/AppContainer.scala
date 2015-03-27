package me.yzhi.twiggy.system

/**
 */
trait AppContainer {
  def name: String
}

object AppContainer {
  def create(args: Array[String]): AppContainer = {
    null
  }
}
