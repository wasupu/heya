package com.wasupu.heya.acceptanceTests.heartbeat

import com.ning.http.client.Response
import com.wasupu.heya.acceptanceTests.sharedContent.SharedContext
import dispatch.Defaults._
import dispatch._
import cucumber.api.scala.{EN, ScalaDsl}
import org.scalatest.Matchers
import scala.concurrent.Future
import org.json4s.jackson.JsonMethods._

class RequestHeartbeat extends ScalaDsl with EN with Matchers {

  When( """^I request a heartbeat$""") {
    def request: Req = url(uri + "/api/heartbeat").GET
    val getResponse: Future[Response] = Http(request)

    val response: Option[Res] = Option(getResponse())
    SharedContext.save("response",response)
  }

  var uri: String = System.getenv().getOrDefault("heyaUri", "http://127.0.0.1:8080")

  implicit val formats = org.json4s.DefaultFormats
}
