package uk.gov.hmrc.perftests.digitaltariffs

import io.gatling.core.action.builder.PauseBuilder
import io.gatling.core.Predef._
import uk.gov.hmrc.performance.conf.ServicesConfiguration

import scala.concurrent.duration._
import scala.util.Random

trait CommonServices extends ServicesConfiguration {

  protected val traderUiBaseUrl = baseUrlFor("binding-tariff-trader-frontend") + "/binding-tariff-application"
  protected val authUrl = baseUrlFor("auth-login-stub") + "/auth-login-stub"

  protected val eoriNumber = "AA000111222"

  protected val userAgent = "DigitalTariffs-PerformanceTests"

  protected def pause = new PauseBuilder(Random.nextInt(2000) milliseconds, None)
}
