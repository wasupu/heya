package com.wasupu.heya.acceptanceTests

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features"),
  strict = true,
  format = Array("pretty", "html:target/cucumber-report"),
  glue = Array("com.wasupu.heya.acceptanceTests"),
  tags = Array("~@pending")
)
class RunCukesTest {

}
