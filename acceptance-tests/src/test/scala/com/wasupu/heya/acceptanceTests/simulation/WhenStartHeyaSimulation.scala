package com.wasupu.heya.acceptanceTests.simulation

import com.ning.http.client.Response
import com.wasupu.heya.acceptanceTests.sharedContent.SharedContext
import cucumber.api.DataTable
import cucumber.api.scala.{EN, ScalaDsl}
import dispatch.Defaults._
import dispatch.{Http, Req, url, _}
import org.json4s.JsonAST.JObject
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import org.scalatest.Matchers

import scala.concurrent.Future

class WhenStartHeyaSimulation extends ScalaDsl with EN with Matchers {

  When("""^I send the start simulation command$"""){ () =>

  def request: Req = url(uri + "/api/simulation").POST
      .addHeader("Content-type", "application/json")

    val getResponse: Future[Response] = Http(request)
    val response: Option[Response] = Option(getResponse())

    SharedContext.save("response",response)
  }

  var uri: String = System.getenv().getOrDefault("heyaUri", "http://127.0.0.1:8080")
}