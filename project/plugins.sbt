resolvers += Resolver.url("hmrc-sbt-plugin-releases",
  url("https://dl.bintray.com/hmrc/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

addSbtPlugin("io.gatling" % "gatling-sbt" % "2.1.7")
addSbtPlugin("uk.gov.hmrc" % "sbt-settings" % "3.9.0")
