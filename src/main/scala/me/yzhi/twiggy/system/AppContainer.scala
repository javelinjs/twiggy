package me.yzhi.twiggy.system

/**
 */
trait AppContainer extends Customer {
  def name: String
}

object AppContainer {
  def create(args: Array[String]): AppContainer = {
    null
  }
}
