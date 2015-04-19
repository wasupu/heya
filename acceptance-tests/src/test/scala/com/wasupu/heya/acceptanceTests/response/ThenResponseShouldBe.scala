package com.wasupu.heya.acceptanceTests.response

import com.wasupu.heya.acceptanceTests.sharedContent.SharedContext
import cucumber.api.scala.{EN, ScalaDsl}
import dispatch._
import org.scalatest.Matchers

class ThenResponseShouldBe extends ScalaDsl with EN with Matchers{

  Then( """^the response code should be (.*)$""") { (statusCode: String) =>
    val mayResponse: Some[Res] = SharedContext.load("response").asInstanceOf[Some[Res]]
    mayResponse.map(response => response.getStatusCode should equal (statusCode.toInt))
  }
}
