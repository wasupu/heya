package com.wasupu.heya.acceptanceTests.simulation

import com.wasupu.heya.acceptanceTests.sharedContent.SharedContext
import cucumber.api.scala.{EN, ScalaDsl}
import dispatch._
import org.scalatest.Matchers
import org.json4s.jackson.JsonMethods._

class ThenThereAreActiveRobots extends ScalaDsl with EN with Matchers {

  Then( """^there (\d+) robot.* active in the simulation$""") { (numberOfRobots: Int) =>
    val mayResponse: Some[Res] = SharedContext.load("response").asInstanceOf[Some[Res]]
    mayResponse.map(response => {
      val bodyData = parse(response.getResponseBody).extract[Map[String, _]]
      bodyData should not be (null)

      val maybeRobots = bodyData.get("robots")

      maybeRobots.foreach((robots: Any) => {
        robots.asInstanceOf[Seq[Map[String,_]]].length should be equals (1L)
      })
    })
  }

  implicit val formats = org.json4s.DefaultFormats.withBigDecimal
}
