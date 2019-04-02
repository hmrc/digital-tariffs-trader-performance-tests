package uk.gov.hmrc.perftests.digitaltariffs.adviceui

import io.gatling.core.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.digitaltariffs.DigitalTariffsPerformanceTestRunner
import uk.gov.hmrc.perftests.digitaltariffs.adviceui.AdviceUiRequests._

class AdviceUiSimulation extends PerformanceTestRunner with DigitalTariffsPerformanceTestRunner {

  override val httpProtocol: HttpProtocolBuilder = {
    buildHttpProtocol(url = externalBaseUrl)
  }

  private val scn =
    scenario("UK Trader asks advice")
      .exec(getStartPage).exec(pause(waitTime))
      .exec(getContactDetails).exec(pause(waitTime))
      .exec(postContactDetails).exec(pause(waitTime))
      .exec(postGoodDetails).exec(pause(waitTime))
      .exec(postSupportingDocuments).exec(pause(waitTime))
      .exec(postSupportingInformation).exec(pause(waitTime))
      .exec(checkYourAnswers).exec(pause(waitTime))

  // TODO: when the `performance-test-runner` is fixed, we should use it here
  setUp(scn.inject(simulationSteps))
    .protocols(httpProtocol)
    .assertions(simulationAssertion)

}
