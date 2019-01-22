
name := "digital-tariffs-performance-tests"

version := "1.0"

scalaVersion := "2.11.11"

val gatlingVersion = "2.2.5" // TODO: test with 3.0.2

libraryDependencies ++= Seq(
  "com.typesafe.play" % "play-json_2.11" % "2.4.3",
  "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion,
  "io.gatling" % "gatling-test-framework" % gatlingVersion,
  "uk.gov.hmrc" %% "performance-test-runner" % "3.2.0"
)

parallelExecution in Test := false

resolvers := Seq(Resolver.bintrayRepo("hmrc", "releases"))
