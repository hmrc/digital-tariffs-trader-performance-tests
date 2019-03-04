package uk.gov.hmrc.perftests.digitaltariffs.traderui

import uk.gov.hmrc.perftests.digitaltariffs.traderui.AuthRequests._
import uk.gov.hmrc.perftests.digitaltariffs.traderui.TraderUiRequests._
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import uk.gov.hmrc.perftests.digitaltariffs.CommonServices

class TraderUiSimulation extends PerformanceTestRunner with CommonServices {

  override val httpProtocol: HttpProtocolBuilder = http.userAgentHeader(userAgent)

  setup("government-gateway-login", "Government Gateway Login") withRequests (
    getGovGatewaySignIn,
    postGovGatewaySignIn
  )

  setup("trader-BTI-application", "Trader applies for a ruling commodity code") withActions (
    getStartPage, pause,
    getBeforeYouStart, pause,
    postBeforeYouStart, pause,
    postRegisterForEori, pause,
    postEnterContactDetails, pause,
    postWhichBestDescribesYou, pause,
    postSelectApplicationType, pause,
    postAcceptItemInfoList, pause,
    postInformationAboutYourItem, pause,
    postConfidentialInformation, pause,
    postDescribeYourItem, pause,
    postSupportingMaterialFileList, pause,
    postCommodityCodeBestMatch, pause,
    postCommodityCodeDigits, pause,
    postWhenToSendSample, pause,
    postReturnSample, pause,
    postSimilarItemCommodityCode, pause,
    postLegalChallenge, pause,
    postSupportingInformation, pause,
    postSupportingInformationDetails, pause,
    postCheckYourAnswers, pause,
    postDeclaration
  )

  runSimulation()
}
