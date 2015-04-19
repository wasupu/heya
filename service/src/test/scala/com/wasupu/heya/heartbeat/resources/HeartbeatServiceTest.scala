package com.wasupu.heya.heartbeat.resources

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.testkit.TestActorRef
import akka.util.Timeout
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}
import spray.http._

import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.Success

@RunWith(classOf[JUnitRunner])
class HeartbeatServiceTest extends FlatSpec with Matchers {

  "Heartbeat actor" should "respond with a HttpResponse with a 200 status code" in {
    implicit val timeout = Timeout(timeoutMillis millis)
    val future = actorRef ? HttpRequest(HttpMethods.GET, "/api/heartbeat")

    val Success(response: HttpResponse) = future.value.get

    response.status should be (StatusCodes.OK)
  }

  implicit lazy val system = ActorSystem()
  val actorRef = TestActorRef[HeartbeatService]
  val timeoutMillis: Long = 800
}