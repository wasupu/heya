package com.wasupu.heya.simulation.domain

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.pattern.ask
import akka.testkit.{TestKit, TestProbe}
import akka.util.Timeout
import com.wasupu.heya.simulation.domain.WorldProtocol.WordStatus
import org.scalatest.{FlatSpecLike, Matchers}

import scala.concurrent.duration._
import scala.language.postfixOps

class WorldTest extends TestKit(ActorSystem("testsystem")) with FlatSpecLike with Matchers {

  it should "create an square world with an unique explorer " in {
    val robot = TestProbe()
    createWorld(robot.ref) ! "start"

    robot.expectMsg("start")
  }

  it should "Return the number of robots" in {
    (createWorld() ? "status")
      .mapTo[WordStatus]
      .map(status => status.numberOfRobots == 1)
  }

  private def createWorld(probe:ActorRef = TestProbe().ref): ActorRef = {
    system.actorOf(Props(new World{
      override def createRobot() = {
        probe
      }
    }))
  }

  private implicit val executionContext = system.dispatcher

  implicit val timeout = Timeout(500 millis)
}
