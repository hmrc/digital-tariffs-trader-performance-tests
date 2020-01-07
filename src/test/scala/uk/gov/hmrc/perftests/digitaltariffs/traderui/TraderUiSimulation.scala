package uk.gov.hmrc.perftests.digitaltariffs.traderui

import io.gatling.core.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.digitaltariffs.DigitalTariffsPerformanceTestRunner
import uk.gov.hmrc.perftests.digitaltariffs.traderui.AuthRequests._
import uk.gov.hmrc.perftests.digitaltariffs.traderui.TraderUiRequests._

class TraderUiSimulation extends PerformanceTestRunner with DigitalTariffsPerformanceTestRunner {

  override val httpProtocol: HttpProtocolBuilder = {
    buildHttpProtocol(url = externalBaseUrl)
  }

  private val scn =
    scenario("UK Trader applies for a BTI application")
      // Government Gateway Sign In
      .exec(getGovGatewaySignIn).exec(pause(waitTime))
      .exec(postGovGatewaySignIn).exec(pause(waitTime))
      // Trader UI journey
      .exec(getStartPage).exec(pause(waitTime))
      .exec(postImportOrExport).exec(pause(waitTime))
      .exec(getInformationYouNeed).exec(pause(waitTime))
      .exec(getRegisterForEori).exec(pause(waitTime))
      .exec(postRegisterForEori).exec(pause(waitTime))
      .exec(postEnterContactDetails).exec(pause(waitTime))
      .exec(postWhichBestDescribesYou).exec(pause(waitTime))
      .exec(postSelectApplicationType).exec(pause(waitTime))
      .exec(getAcceptItemInfoList).exec(pause(waitTime))
      .exec(postDescribeYourItem).exec(pause(waitTime))
      .exec(postSupportingMaterialFileList).exec(pause(waitTime))
      .exec(postCommodityCodeBestMatch).exec(pause(waitTime))
      .exec(postCommodityCodeDigits).exec(pause(waitTime))
      .exec(postWhenToSendSample).exec(pause(waitTime))
      .exec(postReturnSample).exec(pause(waitTime))
      .exec(postSimilarItemCommodityCode).exec(pause(waitTime))
      .exec(postLegalChallenge).exec(pause(waitTime))
      .exec(postSupportingInformation).exec(pause(waitTime))
      .exec(postSupportingInformationDetails).exec(pause(waitTime))
      .exec(getCheckYourAnswers).exec(pause(waitTime))
      .exec(getDeclaration).exec(pause(waitTime))
      .exec(postDeclaration).exec(pause(waitTime))
      .exec(getConfirmation).exec(pause(waitTime))

  // TODO: when the `performance-test-runner` is fixed, we should use it here
  setUp(scn.inject(simulationSteps))
    .protocols(httpProtocol)
    .assertions(simulationAssertion)

}
