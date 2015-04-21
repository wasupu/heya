package com.wasupu.heya.resources

import akka.actor.Props
import com.wasupu.heya.heartbeat.resources.HeartbeatService
import spray.routing.HttpServiceActor

class ApiRouter extends HttpServiceActor {

  def receive = runRoute {
    pathPrefix("api") {
      path("heartbeat") {
        ctx => heartbeatService ! ctx
      } ~
      path("simulation"){
        ctx => simulationService ! ctx
      }
    }
  }

  private val heartbeatService = context.system.actorOf(Props[HeartbeatService])
  private val simulationService = context.system.actorOf(Props[SimulationService])
}
