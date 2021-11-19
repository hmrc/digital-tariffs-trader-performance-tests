/*
 * Copyright 2021 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
