package com.wasupu.heya.resources.resources

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{TestActorRef, TestKit, TestProbe}
import akka.util.Timeout
import com.wasupu.heya.resources.SimulationService
import com.wasupu.heya.simulation.domain.WorldProtocol.WordStatus
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods._
import org.scalatest.{FlatSpecLike, Matchers}
import spray.http._
import akka.pattern.ask

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

  "Simulation service actor" should "respond with a HttpResponse with a 200 status code when request status" in {
    implicit val timeout = Timeout(timeoutMillis millis)
    val worldProbe = TestProbe()
    val actorRef = TestActorRef(Props(new SimulationServiceStub(worldProbe.ref)))
    val future = actorRef ? getSimulationStatusRequest

    worldProbe.expectMsg("status")
    worldProbe.reply(WordStatus(1))

    val Success(response: HttpResponse) = future.value.get
    response.status should be(StatusCodes.OK)
  }

  "Simulation service actor" should "retrieve the simulation status" in {
    implicit val timeout = Timeout(timeoutMillis millis)

    val worldProbe = TestProbe()

    val actorRef = TestActorRef(Props(new SimulationServiceStub(worldProbe.ref)))
    actorRef ? getSimulationStatusRequest

    worldProbe.expectMsg(500 millis, "status")
  }

  it should "return the number of robots" in {
    implicit val timeout = Timeout(timeoutMillis millis)
    val worldProbe = TestProbe()
    val actorRef = TestActorRef(Props(new SimulationServiceStub(worldProbe.ref)))
    val future = actorRef ? getSimulationStatusRequest

    worldProbe.expectMsg("status")
    worldProbe.reply(WordStatus(1))

    val Success(response: HttpResponse) = future.value.get

    val responseBody = parse(response.entity.asString).extract[Map[String, _]]

    val maybeRobots: Option[Seq[Map[String, _]]] = responseBody.get("robots").asInstanceOf[Option[Seq[Map[String, _]]]]

    maybeRobots.foreach(robots =>{
      robots.length should be equals(1L)
    })
  }

  def createSimulationRequest: HttpRequest = {
    HttpRequest(HttpMethods.POST, "/api/simulation")
  }

  def deleteSimulationRequest: HttpRequest = {
    HttpRequest(HttpMethods.DELETE, "/api/simulation")
  }

  def getSimulationStatusRequest: HttpRequest = {
    HttpRequest(HttpMethods.GET, "/api/simulation")
  }

  private class SimulationServiceStub(wold: ActorRef = TestProbe().ref) extends SimulationService {
    override def createWorld: ActorRef = wold
  }

  val timeoutMillis: Long = 800

  private implicit val formats = DefaultFormats.withBigDecimal
}