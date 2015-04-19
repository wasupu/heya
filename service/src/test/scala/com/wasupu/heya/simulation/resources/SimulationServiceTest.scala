package com.wasupu.heya.calculator.resources

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.pattern.ask
import akka.testkit.{TestActorRef, TestKit, TestProbe}
import akka.util.Timeout
import org.json4s.DefaultFormats
import org.scalatest.{FlatSpecLike, Matchers}
import spray.http._

import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.Success

class SimulationServiceTest extends TestKit(ActorSystem("testing")) with FlatSpecLike with Matchers {

  "Simulation service actor" should "respond with a HttpResponse with a 201 status code" in {
    implicit val timeout = Timeout(timeoutMillis millis)
    val worldProbe = TestProbe()
    val actorRef = TestActorRef(Props(new SimulationServiceStub(worldProbe.ref)))
    val future = actorRef ? createSimulationRequest

    worldProbe.expectMsg("start")

    val Success(response: HttpResponse) = future.value.get
    response.status should be(StatusCodes.Created)
  }

  "Simulation service actor" should "start the simulation" in {
    implicit val timeout = Timeout(timeoutMillis millis)

    val worldProbe = TestProbe()

    val actorRef = TestActorRef(Props(new SimulationServiceStub(worldProbe.ref)))
    actorRef ? createSimulationRequest

    worldProbe.expectMsg(500 millis, "start")
  }

  "Simulation service actor" should "respond with a HttpResponse with a 204 status code when stop" in {
    implicit val timeout = Timeout(timeoutMillis millis)
    val worldProbe = TestProbe()
    val actorRef = TestActorRef(Props(new SimulationServiceStub(worldProbe.ref)))
    val future = actorRef ? deleteSimulationRequest

    worldProbe.expectMsg("stop")

    val Success(response: HttpResponse) = future.value.get
    response.status should be(StatusCodes.NoContent)
  }

  "Simulation service actor" should "stop the simulation" in {
    implicit val timeout = Timeout(timeoutMillis millis)

    val worldProbe = TestProbe()

    val actorRef = TestActorRef(Props(new SimulationServiceStub(worldProbe.ref)))
    actorRef ? deleteSimulationRequest

    worldProbe.expectMsg(500 millis, "stop")
  }

  def createSimulationRequest: HttpRequest = {
    HttpRequest(HttpMethods.POST, "/api/simulation")
  }

  def deleteSimulationRequest: HttpRequest = {
    HttpRequest(HttpMethods.DELETE, "/api/simulation")
  }

  private class SimulationServiceStub(wold: ActorRef = TestProbe().ref) extends SimulationService {
    override def createWorld: ActorRef = wold
  }

  val timeoutMillis: Long = 800

  private implicit val formats = DefaultFormats.withBigDecimal
}