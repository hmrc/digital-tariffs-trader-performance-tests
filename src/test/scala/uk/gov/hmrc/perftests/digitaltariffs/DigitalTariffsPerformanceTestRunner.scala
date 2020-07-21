package uk.gov.hmrc.perftests.digitaltariffs

import io.gatling.core.Predef._
import io.gatling.core.controller.inject.InjectionStep
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner

import scala.concurrent.duration._

trait DigitalTariffsPerformanceTestRunner extends PerformanceTestRunner with ServicesConfiguration {

  protected def buildHttpProtocol(url: String): HttpProtocolBuilder = {
    http
      .userAgentHeader("DigitalTariffs-PerformanceTests")
      .connectionHeader("close")
      .baseURL(url)
  }

  protected val adminBaseUrl = "https://admin.staging.tax.service.gov.uk"
  protected val externalBaseUrl = "https://www.staging.tax.service.gov.uk"

  protected val authStubBaseUrl = baseUrlFor("auth-login-stub") + "/auth-login-stub"
  protected val traderUiBaseUrl = baseUrlFor("binding-tariff-trader-frontend") + "/advance-tariff-application"

  protected val eoriNumber = "AA000111222"

  protected val waitTime = 1.seconds

  protected val rate = 0.5D
  protected val rampInterval = 1.minute  // 5.seconds
  protected val mainInterval = 8.minutes // 15.seconds

  protected def simulationSteps: Seq[InjectionStep] =
    Seq(
      rampUsersPerSec(0).to(rate).during(rampInterval), // growth
      constantUsersPerSec(rate).during(mainInterval),          // constant
      rampUsersPerSec(rate).to(0).during(rampInterval)  // shutting down
    )

  protected def simulationAssertion: Seq[Assertion] =
    Seq(
      global.successfulRequests.percent.gte(99.0)
    )

}
