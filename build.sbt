// heya

lazy val commonSettings = Seq(
  organization := "com.wasupu",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.5",
  scalacOptions ++= Seq("-feature")
)

lazy val heya = (project in file(".")).
  aggregate(service, acceptanceTests).
  settings(commonSettings: _*).
  settings(
    name := "wasupu.heya"
  )

lazy val service = Project("service", file("service")).
  settings(commonSettings: _*)

lazy val acceptanceTestCfg = config("acceptance") extend (Test)

lazy val acceptanceTests = Project("acceptance-tests", file("acceptance-tests")).
  configs(acceptanceTestCfg).
  settings(commonSettings: _*)