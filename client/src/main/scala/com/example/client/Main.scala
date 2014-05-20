package com.example.client

import akka.actor._
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import java.util.Scanner

object Main extends App {

  val conf = ConfigFactory.load("client")
  val system = ActorSystem("ClientSystem", conf)

  val input = new Scanner(System.in)
  val localActor = system.actorOf(Props[ClientActor], name = "ClientActor")
  Thread.sleep(1000)

  var shutdown = false
  while (!shutdown) {
    print("> ")
    val read = input.next()

    println(s"You typed: $read")
    read match {
      case "stop" =>
        system.stop(localActor)
        Thread.sleep(1000)
        system.shutdown
        shutdown = true
      case "send" =>
        localActor ! "Hello"
      case _ =>
        println("Unknown command")
    }
  }
}

class ClientActor extends Actor {
  println("ClientActor created")
  val conf = ConfigFactory.load("client")
  val endpoint = conf.getString("server.endpoint")
  println(s"Quering $endpoint")

  val remoteActor = context.actorSelection(endpoint)
  var counter = 0

  def receive = {
    case message: String =>
      println(s"LocalActor received message: $message")
      remoteActor ! message
  }
}
