package uk.gov.hmrc.perftests.digitaltariffs.rulingui

import io.gatling.core.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.digitaltariffs.DigitalTariffsPerformanceTestRunner
import uk.gov.hmrc.perftests.digitaltariffs.rulingui.RulingUiRequests._

class RulingUiSimulation extends PerformanceTestRunner with DigitalTariffsPerformanceTestRunner {

  override val httpProtocol: HttpProtocolBuilder = {
    buildHttpProtocol(url = externalBaseUrl)
  }

  private val scn =
    scenario("UK Trader searches for existing rulings")
      .exec(getStartPage).exec(pause(waitTime))
      .exec(searchPage).exec(pause(waitTime))
      .exec(getQueryResultPage).exec(pause(waitTime))

  // TODO: when the `performance-test-runner` is fixed, we should use it here
  setUp(scn.inject(simulationSteps))
    .protocols(httpProtocol)
    .assertions(simulationAssertion)

}
