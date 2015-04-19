package com.wasupu.heya.acceptanceTests.sharedContent

import cucumber.api.scala.ScalaDsl

trait CleanupSharedContextHook extends ScalaDsl {

  After(20) {
    _ => SharedContext.cleanup
  }

}