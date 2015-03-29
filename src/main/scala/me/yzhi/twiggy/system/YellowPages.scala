package me.yzhi.twiggy.system

import me.yzhi.twiggy.system.Node.NodeID

import scala.collection.mutable.Map

/**
 */
class YellowPages {
  var van: Van = _

  val customers = Map[String, (Customer, Boolean)]()
  val relations = Map[String, Vector[String]]()
  val nodes = Map[NodeID, Node]()

  var numWorkers_ = 0
  var numServers_ = 0

  def init(): Unit = {
    // TODO
    van = new Van
  }

  def numWorkers = numWorkers_

  def numServers = numServers_

  def customer(name: String): Option[Customer] = {
    customers.get(name) match {
      case Some((first:Customer, second:Boolean)) => Some(first)
      case _ => None
    }
  }

  def addNode(node: Node): Unit = {
    if (!nodes.contains(node.id)) {
      require(van.connect(node).ok)
      node.role match {
        case Node.WORKER => numWorkers_ += 1
        case Node.SERVER => numServers_ += 1
      }
    }
    nodes.put(node.id, node)
  }

  def children(parent: String) = {
    relations.get(parent)
  }
}
