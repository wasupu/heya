package com.wasupu.heya.calculator.domain

import akka.actor.Actor
import com.wasupu.heya.calculator.domain.WorldProtocol.Status

object WorldProtocol{

  case class Status(numberOfRobots:Int)

}

class World extends Actor {

  override def receive: Receive = {
    case "start" => {

    }
    case "status" =>{
      sender() ! Status(1)
    }
  }
}
