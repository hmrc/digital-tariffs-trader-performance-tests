package uk.gov.hmrc.perftests.digitaltariffs

import io.gatling.core.Predef._
import io.gatling.core.check.CheckBuilder
import io.gatling.core.check.regex.RegexCheckType
import io.gatling.core.controller.inject.open.OpenInjectionStep
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
      .baseUrl(url)
  }

  protected val externalBaseUrl = "https://www.staging.tax.service.gov.uk"

  protected val authStubBaseUrl: String = baseUrlFor("auth-login-stub") + "/auth-login-stub"
  protected val traderUiBaseUrl: String = baseUrlFor("binding-tariff-trader-frontend") + "/advance-tariff-application"

  protected val eoriNumber = "AA000111222"

  protected val waitTime: FiniteDuration = 1.seconds

  protected val rate = 0.5D
  protected val rampInterval: FiniteDuration = 1.minute // 5.seconds
  protected val mainInterval: FiniteDuration = 8.minutes // 15.seconds

  protected def simulationSteps: Seq[OpenInjectionStep] =
    Seq(
      rampUsersPerSec(0).to(rate).during(rampInterval), // growth
      constantUsersPerSec(rate).during(mainInterval), // constant
      rampUsersPerSec(rate).to(0).during(rampInterval) // shutting down
    )

  protected def simulationAssertion: Seq[Assertion] =
    Seq(
      global.successfulRequests.percent.gte(99.0)
    )

  def saveCsrfToken: CheckBuilder[RegexCheckType, String, String] = {
    regex(_ => csrfPattern).saveAs("csrfToken")
  }

  private val csrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)"""

}
