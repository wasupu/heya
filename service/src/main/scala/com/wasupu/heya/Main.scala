package com.wasupu.heya

import akka.actor.{ActorSystem, Props}
import akka.camel.{Camel, CamelExtension}
import akka.io.IO
import akka.kernel.Bootable
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.wasupu.heya.resources.ApiRouter
import org.apache.camel.Exchange
import org.apache.camel.support.TypeConverterSupport
import org.json4s.JsonAST.JValue
import org.json4s.jackson.JsonMethods._
import spray.can.Http

class Main extends Bootable {

  implicit val system = setupCamelContext(ActorSystem("heya"))

  def startup = {

    val api = system.actorOf(Props[ApiRouter])
    IO(Http) ! Http.Bind(api, interface = httpApiHost, port = httpApiPort.toInt)
  }

  def shutdown = {
    system.shutdown()
  }

  // TODO: Maybe ...
  def setupCamelContext(system: ActorSystem): ActorSystem = {
    val camel: Camel = CamelExtension(system)
    val converterRegistry = camel.context.getTypeConverterRegistry
    converterRegistry.addTypeConverter(classOf[String], classOf[JValue], new TypeConverterSupport() {

      val mapper: ObjectMapper with ScalaObjectMapper = new ObjectMapper() with ScalaObjectMapper

      override def convertTo[T](targetType: Class[T], exchange: Exchange, value: scala.Any): T = {
        compact(render(value.asInstanceOf[JValue])).asInstanceOf[T]
      }
    })

    system
  }

  private val httpApiHost: String = System.getenv().getOrDefault("httpApiHost", "0.0.0.0")
  private val httpApiPort: String = System.getenv().getOrDefault("httpApiPort", "8080")

}
