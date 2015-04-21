package com.wasupu.heya.resources

import akka.actor.{ActorRef, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.wasupu.heya.simulation.domain.World
import com.wasupu.heya.simulation.domain.WorldProtocol.WordStatus
import org.json4s.DefaultFormats
import spray.http.StatusCodes
import spray.routing._

import scala.concurrent.Future
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
            val responseFuture: Future[String] = (world ? "status")
              .mapTo[WordStatus]
              .map(worldStatus)
              .map(result => mapper.writeValueAsString(result))

            onSuccess(responseFuture) { response => complete(StatusCodes.OK, response) }
          }
        }
    }
  }

  private def worldStatus(status:WordStatus): Map[String, _] ={
    Map[String, Any](
      "robots" -> Seq("string"))
  }

  protected def createWorld: ActorRef = {
    context.actorOf(Props[World])
  }

  private val world = createWorld

  private implicit val timeout = Timeout(500 millis)

  private implicit val formats = DefaultFormats.withBigDecimal

  private implicit val executionContext = context.dispatcher

  private val mapper = new ObjectMapper() with ScalaObjectMapper
  mapper.registerModule(DefaultScalaModule)
}