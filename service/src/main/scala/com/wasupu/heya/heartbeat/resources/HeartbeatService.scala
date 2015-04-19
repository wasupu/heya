package com.wasupu.heya.heartbeat.resources

import spray.http.StatusCodes
import spray.routing._

class HeartbeatService extends HttpServiceActor {

  def receive = runRoute {
    get {
      complete(StatusCodes.OK)
    }
  }
}
