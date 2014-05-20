package com.example.server

import akka.actor._
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import java.util.Scanner

object Main extends App {

  val conf = ConfigFactory.load("server");
  val systemName = conf.getString("endpoint.actorsystem")
  val actorName = conf.getString("endpoint.actor")
  val system = ActorSystem(systemName, conf)
  val local = system.actorOf(Props[ServerActor], name = actorName)
  local ! "Wake up"
  Thread.sleep(1000)

  val input = new Scanner(System.in)

  var shutdown = false
  while (!shutdown) {
    print("> ")
    val read = input.next()
    read match {
      case "stop" =>
        system.shutdown
        shutdown = true
      case _ =>
        println("Unknown command")
    }
  }
}

class ServerActor extends Actor {
  println(s"ServerActor created: " + this.self.path)
  def receive = {
    case msg: String =>
      println(s"ServerActor received message '$msg'")
  }
}
