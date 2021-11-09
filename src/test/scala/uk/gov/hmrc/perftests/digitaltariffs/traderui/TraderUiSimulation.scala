package uk.gov.hmrc.perftests.digitaltariffs.traderui

import io.gatling.http.protocol.HttpProtocolBuilder
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.digitaltariffs.DigitalTariffsPerformanceTestRunner
import uk.gov.hmrc.perftests.digitaltariffs.traderui.AuthRequests._
import uk.gov.hmrc.perftests.digitaltariffs.traderui.TraderUiRequests._

class TraderUiSimulation extends PerformanceTestRunner with DigitalTariffsPerformanceTestRunner {

  override val httpProtocol: HttpProtocolBuilder = {
    buildHttpProtocol(url = externalBaseUrl)
  }

  setup("traderUI", "UK Trader applies for a BTI application") withRequests(
    // Government Gateway Sign In
    getGovGatewaySignIn,
    postGovGatewaySignIn,
    // Trader UI journey
    getStartPage,
    getInformationYouNeed,
    getInformationMadePublic,
    getGoodsName,
    postGoodsName,
    postGoodsDescription,
    postConfidentialInfo,
    postProvideConfidentialInfo,
    postUploadSupportingDocument,
    postAreYouSendingASample,
    postCouldSampleBeHazardous,
    postReturnSample,
    postHaveYouFoundCommodityCode,
    postCommodityCodeDigits,
    postLegalChallenge,
    postLegalChallengeDetails,
    postPreviousRulingReference,
    postProvidePreviousRulingReference,
    postSimilarRuling,
    postProvideSimilarRulingReference,
    postAddAnotherSimilarRuling,
    postRegisterForEori,
    postEnterContactDetails,
    postCheckYourAnswers,
    getConfirmation
  )

  runSimulation()

}
