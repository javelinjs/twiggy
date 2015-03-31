package me.yzhi.twiggy.system

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import me.yzhi.twiggy.util.FileUtils

import scala.concurrent.Promise

/**
 */
class Postoffice private {
  val yellowPages: YellowPages = _
  var app: AppContainer = _
  var appConf: String = _
  var appMsg: Message = _
  var finishedNodes = 0

  val system = ActorSystem("Postoffice")
  var receiver: ActorRef = _
  var sender: ActorRef = _

  val nodesAreDone = Promise[Unit]()
  val nodesAreReady = Promise[Unit]()
  val initAppPromise = Promise[Unit]()
  val runAppPromise = Promise[Unit]()

  def start(args: Array[String]) = {
    // TODO: parse args and store to CmdOptions
    yellowPages.init()
    myNode match {
      // FIXME
      case Node.SCHEDULER =>
        if (CmdOptions.appFile.isEmpty) {
          require(FileUtils.readFileToString(CmdOptions.appFile, appConf),
            "failed to read conf file %s" format CmdOptions.appFile)
        } else {
          appConf = CmdOptions.appConf
        }
        app = AppContainer.create(args)
        require(app != null, "failed to create %s with conf %s" format (CmdOptions.appName, CmdOptions.appConf))
      case _ =>
        // connect to the scheduler, which will send back a create_app request
        val task = new Task(opt=Task.MANAGE, request=true, time=0)
        task.mngNode = new ManageNode(ManageNode.CONNECT)
        task.mngNode.nodes :+= myNode
        val msg = new Message(task)
        msg.recver = yellowPages.van.scheduler.id
        send(msg)
    }

    receiver = system.actorOf(Props(new Receiver))
    sender = system.actorOf(Props(new Sender))

    myNode match {
      // FIXME
      case Node.SCHEDULER =>
        // add my node into app_
        // TODO: time = 0 ?
        val task = new Task(opt=Task.MANAGE, request=true, time=0)
        task.customer = app.name
        task.mngNode = new ManageNode(ManageNode.ADD)
        task.mngNode.nodes :+= myNode
        manageNode(task)
        // TODO: init other nodes
      case _ =>
        // TODO
    }
  }

  def manageApp(msg: Message): Unit = {
    val tk = msg.task
    require(tk.mngApp != null)
    val cmd = tk.mngApp.cmd
    cmd match {
      case ManageApp.ADD =>
        app = AppContainer.create(tk.customer, tk.mngApp.conf)
        // yp().depositCustomer(app->name());
      case ManageApp.INIT =>
        msg.finished = false
        if (appMsg == null) {
          appMsg = new Message(msg)
        }
        initAppPromise.success()
      case ManageApp.RUN =>
        msg.finished = false
        if (appMsg == null) {
          appMsg = new Message(msg)
        }
        runAppPromise.success()
      case ManageApp.DONE =>
        finishedNodes += 1
        if (finishedNodes >= CmdOptions.numWorkers + CmdOptions.numServers) {
          nodesAreDone.success()
        }
    }
  }

  def manageNode(tk: Task): Unit = {
    // CHECK(tk.has_mng_node());
    val mng = tk.mngNode
    mng.cmd match {
      case ManageNode.CONNECT =>
        // FIXME:
        require(myNode == Node.SCHEDULER)
        // TODO: CHECK_EQ(mng.node_size(), 1);
        // first add this node into app
        val add = tk
        require(app.name != null)
        add.customer = app.name
        val mngNode = new ManageNode(ManageNode.ADD)
        add.mngNode = mngNode
        manageNode(add)
        // create the app in this node
        val task = new Task(request=true, opt=Task.MANAGE, time=1)
        task.customer = app.name
        task.mngApp = new ManageApp(ManageApp.ADD)
        task.mngApp.conf = appConf
        app.port(mng.nodes.head.id).submit(task)
        // check if all nodes are connected
        if (yellowPages.numWorkers >= CmdOptions.numWorkers &&
          yellowPages.numServers >= CmdOptions.numServers) {
          nodesAreReady.success()
        }
        tk.customer = app.name // otherwise the remote node doesn't know
        // how to find the according customer
      case ManageNode.ADD | ManageNode.UPDATE =>
        val obj = yellowPages.customer(tk.customer)
        require(obj != None, "customer [" + tk.customer + "] doesn't exists")
        mng.nodes.foreach(node => {
          yellowPages.addNode(node)
          obj.get.exec.add(node)
          yellowPages.children(obj.get.name).foreach(_.foreach(c => {
            val child = yellowPages.customer(c)
            child.foreach(_.exec.add(node))
          }))
        })
      case ManageNode.REPLACE =>
      case ManageNode.REMOVE =>
        // do nothing
    }
  }

  def send(msg: Message) {
    if (msg.valid && !msg.terminate) {
      val stat = yellowPages.van.send(msg, 0)
      for (s <- stat if s.ok) {
        // heartbeat_info_.increaseOutBytes(send_bytes);
        return
      }
      // TODO: log failure
    } else {
      // TODO: do not send, fake a reply mesage
    }
  }

  private def myNode = {
    yellowPages.van.myNode
  }

  class Receiver extends Actor {
    // TODO
    override def receive: Receive = ???
  }

  class Sender extends Actor {
    // TODO
    override def receive: Receive = ???
  }
}

object Postoffice {
  val instance = new Postoffice()
}
