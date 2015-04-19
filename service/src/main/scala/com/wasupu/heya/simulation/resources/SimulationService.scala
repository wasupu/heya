package com.wasupu.heya.calculator.resources

import akka.actor.{ActorRef, Props}
import akka.util.Timeout
import com.wasupu.heya.calculator.domain.World
import org.json4s.DefaultFormats
import spray.http.StatusCodes
import spray.routing._

import scala.concurrent.duration._
import scala.language.postfixOps

class SimulationService extends HttpServiceActor {

  def receive = runRoute {
    dynamic {
      delete {
        dynamic {
          world ! "stop"
          complete(StatusCodes.NoContent)
        }
      } ~
        post {
          dynamic {
            world ! "start"
            complete(StatusCodes.Created)
          }
        } ~
        get {
          dynamic {
            world ! "status"
            complete(StatusCodes.OK)
          }
        }
    }
  }

  protected def createWorld: ActorRef = {
    context.actorOf(Props[World])
  }

  private val world = createWorld

  private implicit val timeout = Timeout(500 millis)

  private implicit val formats = DefaultFormats.withBigDecimal

  private implicit val executionContext = context.dispatcher
}