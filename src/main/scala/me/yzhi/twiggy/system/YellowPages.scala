package me.yzhi.twiggy.system

import me.yzhi.twiggy.system.Node.NodeID

import scala.collection.mutable

/**
 */
class YellowPages {
  var van: Van = new Van()

  val customers_ = mutable.Map[String, (Customer, Boolean)]()
  val relations_ = mutable.Map[String, Vector[String]]()
  val nodes_ = mutable.Map[NodeID, Node]()

  var numWorkers_ = 0
  var numServers_ = 0

  def init(): Unit = {
    // TODO
    van = new Van
  }

  def numWorkers = numWorkers_

  def numServers = numServers_

  def customer(name: String): Option[Customer] = {
    customers_.get(name) match {
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
    nodes_.put(node.id, node)
  }

  def children(parent: String) = {
    relations_.get(parent)
  }

  def nodes = {
    Vector() ++ nodes_.values
  }
}
