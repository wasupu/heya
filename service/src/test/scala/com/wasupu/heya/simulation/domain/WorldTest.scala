package com.wasupu.heya.calculator.domain

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.pattern.ask
import akka.testkit.TestKit
import akka.util.Timeout
import org.scalatest.{FlatSpecLike, Matchers}

import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.Success

class WorldTest extends TestKit(ActorSystem("testsystem")) with FlatSpecLike with Matchers {

  it should "create an square world with an unique explorer " in{

    implicit val timeout = Timeout(500 millis)
    implicit val executionContext = system.dispatcher

    createWorld ! "start"

  }

  private def createWorld: ActorRef = {
    system.actorOf(Props[World])
  }
}
