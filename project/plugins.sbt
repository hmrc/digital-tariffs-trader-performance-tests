resolvers += "HMRC-open-artefacts-maven" at "https://open.artefacts.tax.service.gov.uk/maven2"
resolvers += Resolver.url("HMRC-open-artefacts-ivy", url("https://open.artefacts.tax.service.gov.uk/ivy2"))(Resolver.ivyStylePatterns)

addSbtPlugin("io.gatling" % "gatling-sbt" % "3.2.1")
addSbtPlugin("uk.gov.hmrc" % "sbt-settings" % "4.9.0")
addSbtPlugin("uk.gov.hmrc" % "sbt-auto-build" % "2.15.0")
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.5.0")
