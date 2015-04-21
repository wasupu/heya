package com.wasupu.heya.simulation.domain

import akka.actor.{ActorRef, Actor, Props}
import com.wasupu.heya.simulation.domain.WorldProtocol.WordStatus

object WorldProtocol{

  case class WordStatus(numberOfRobots:Int)

}

class World extends Actor {

  override def receive: Receive = {
    case "start" => {
      robot ! "start"
    }

    case "status" =>{
      sender() ! WordStatus(1)
    }
  }

  protected def createRobot():ActorRef  = {
    context.system.actorOf(Props[Robot])
  }

  private val robot: ActorRef = createRobot()
}
