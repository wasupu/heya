name := "heya-acceptance-tests"

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-jackson" % "3.2.11" % Test,
  "info.cukes" %% "cucumber-scala" % "1.2.2" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "info.cukes" % "cucumber-junit" % "1.2.2" % Test,
  "org.scalatest" %% "scalatest" % "2.2.0" % Test,
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2" % Test
)

publishLocal := {}

publishM2 := {}

publish := {}

test := {}