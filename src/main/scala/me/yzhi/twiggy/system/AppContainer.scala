package me.yzhi.twiggy.system

/**
 */
trait AppContainer extends Customer {
  def name: String
  def init(): Unit
  def run(): Unit
}

object AppContainer {
  def create(args: Array[String]): AppContainer = {
    null
  }

  def create(name: String, conf: String): AppContainer = {
    null
  }
}
