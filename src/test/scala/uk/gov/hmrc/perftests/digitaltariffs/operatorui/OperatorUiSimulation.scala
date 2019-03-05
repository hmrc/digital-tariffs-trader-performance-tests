package uk.gov.hmrc.perftests.digitaltariffs.operatorui

import io.gatling.core.Predef._
import io.gatling.http.Predef.flushCookieJar
import io.gatling.http.protocol.HttpProtocolBuilder
import uk.gov.hmrc.perftests.digitaltariffs.operatorui.OperatorUiRequests._
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.digitaltariffs.DigitalTariffsPerformanceTestRunner
import uk.gov.hmrc.perftests.digitaltariffs.operatorui.StrideAuthRequests._

class OperatorUiSimulation extends PerformanceTestRunner with DigitalTariffsPerformanceTestRunner {

  override val httpProtocol: HttpProtocolBuilder = {
    buildHttpProtocol(url = "https://admin.staging.tax.service.gov.uk")
  }

  private val scn =
    scenario("HMRC Operator review a BTI application")
      .exec(flushCookieJar)
      // Stride Auth Sign In
      .exec(getProtectedPageNoSession).exec(pause(waitTime))
      .exec(getStrideSignIn).exec(pause(waitTime))
      .exec(getIdpSignInPage).exec(pause(waitTime))
      .exec(postIdpSignInPage).exec(pause(waitTime))
      .exec(getSignInRedirect).exec(pause(waitTime))
      .exec(postIdpResponseToStride).exec(pause(waitTime))
      // HMRC Operator UI journey
      .exec(getStartPage).exec(pause(waitTime))
      .exec(getGatewayQueue).exec(pause(waitTime))
      .exec(getCarsQueue).exec(pause(waitTime))
      .exec(getStartPage).exec(pause(waitTime))
      .exec(getGatewayQueue).exec(pause(waitTime))
      .exec(getCarsQueue).exec(pause(waitTime))
      .exec(getElmQueue).exec(pause(waitTime))
      .exec(getActQueue).exec(pause(waitTime))
      .exec(getCapQueue).exec(pause(waitTime))
      .exec(getMyCases).exec(pause(waitTime))
      .exec(getCaseTraderDetails).exec(pause(waitTime))
      .exec(getCaseApplicationDetails).exec(pause(waitTime))
      .exec(getCaseAttachments).exec(pause(waitTime))
      .exec(getCaseActivity).exec(pause(waitTime))
      .exec(getCaseKeywords).exec(pause(waitTime))
      .exec(getCaseRuling).exec(pause(waitTime))

  // TODO: when the `performance-test-runner` is fixed, we should use it here
    setUp(scn.inject(simulationSteps))
      .protocols(httpProtocol)
      .assertions(simulationAssertion)

}
