name := "heya"

enablePlugins(AkkaAppPackaging)

mainClass in Compile := Some("com.wasupu.heya.Main")

addCommandAlias("package", ";stage;universal:packageZipTarball")

lazy val akkaVersion = "2.3.9"

lazy val sprayVersion = "1.3.2"

libraryDependencies ++= Seq(
  "io.spray" %% "spray-can" % sprayVersion,
  "io.spray" %% "spray-routing" % sprayVersion,
  "com.typesafe.akka" %% "akka-kernel" % akkaVersion,
  "com.typesafe.akka" %% "akka-camel" % akkaVersion,
  "org.json4s" %% "json4s-jackson" % "3.2.11",
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "io.spray" %% "spray-testkit" % sprayVersion % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "org.scalatest" %% "scalatest" % "2.2.1" % Test,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.5.0"
)