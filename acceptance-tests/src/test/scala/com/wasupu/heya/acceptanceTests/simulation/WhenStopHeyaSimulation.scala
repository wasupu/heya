package com.wasupu.heya.acceptanceTests.simulation

import com.ning.http.client.Response
import com.wasupu.heya.acceptanceTests.sharedContent.SharedContext
import cucumber.api.scala.{EN, ScalaDsl}
import dispatch.Defaults._
import dispatch.{Http, Req, url, _}
import org.scalatest.Matchers

import scala.concurrent.Future

class WhenStopHeyaSimulation extends ScalaDsl with EN with Matchers {

  When( """^I send the stop simulation command$""") { () =>
    def request: Req = url(uri + "/api/simulation").DELETE
      .addHeader("Content-type", "application/json")

    val getResponse: Future[Response] = Http(request)
    val response: Option[Response] = Option(getResponse())

    SharedContext.save("response", response)
  }

  var uri: String = System.getenv().getOrDefault("heyaUri", "http://127.0.0.1:8080")
}