package me.yzhi.twiggy.system

import me.yzhi.twiggy.system.Node.NodeID

import scala.collection.mutable

/**
 */
class YellowPages {
  var _van: Van = new Van()

  val _customers = mutable.Map[String, (Customer, Boolean)]()
  val _relations = mutable.Map[String, Vector[String]]()
  val _nodes = mutable.Map[NodeID, Node]()

  var _numWorkers = 0
  var _numServers = 0

  def init(): Unit = {
    // TODO
    _van = new Van
  }

  def numWorkers = _numWorkers

  def numServers = _numServers

  def customer(name: String): Option[Customer] = {
    _customers.get(name) match {
      case Some((first:Customer, second:Boolean)) => Some(first)
      case _ => None
    }
  }

  def addNode(node: Node): Unit = {
    if (!_nodes.contains(node.id)) {
      require(_van.connect(node).ok)
      node.role match {
        case Node.WORKER => _numWorkers += 1
        case Node.SERVER => _numServers += 1
      }
    }
    _nodes.put(node.id, node)
  }

  def children(parent: String) = {
    _relations.get(parent)
  }

  def nodes = {
    Vector() ++ _nodes.values
  }
}
