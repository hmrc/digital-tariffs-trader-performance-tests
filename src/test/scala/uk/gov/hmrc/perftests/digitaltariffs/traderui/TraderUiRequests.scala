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

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.digitaltariffs.DigitalTariffsPerformanceTestRunner

object TraderUiRequests extends DigitalTariffsPerformanceTestRunner {

  private val homePage = s"$traderUiBaseUrl/applications-and-rulings"

  def getStartPage: HttpRequestBuilder = {
    http("Get start")
      .get(traderUiBaseUrl)
      .check(status.is(200))
      .check(currentLocation.is(homePage))
  }

  def getInformationYouNeed: HttpRequestBuilder = {
    http("Information you need to apply for a ruling")
      .get(s"$traderUiBaseUrl/information-you-need")
      .check(status.is(200))
  }

  def getInformationMadePublic: HttpRequestBuilder = {
    http("Some of the information you provide")
      .get(s"$traderUiBaseUrl/information-may-be-made-public")
      .check(status.is(200))
  }

  def getGoodsName: HttpRequestBuilder = {
    http("Provide a name for the goods")
      .get(s"$traderUiBaseUrl/provide-goods-name")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postGoodsName: HttpRequestBuilder = {
    http("Provide a name for the goods")
      .post(s"$traderUiBaseUrl/provide-goods-name")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("goodsName", "Snow man jacket")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postGoodsDescription: HttpRequestBuilder = {
    http("Provide a detailed description")
      .post(s"$traderUiBaseUrl/provide-goods-description")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("goodsDescription", "Snow man jacket in black colour")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postConfidentialInfo: HttpRequestBuilder = {
    http("Do you want to add any confidential information")
      .post(s"$traderUiBaseUrl/add-confidential-information")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postProvideConfidentialInfo: HttpRequestBuilder = {
    http("Provide any confidential information")
      .post(s"$traderUiBaseUrl/provide-confidential-information")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("confidentialInformation", "I have used colour to pain your snow man")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postUploadSupportingDocument: HttpRequestBuilder = {
    http("Do you want to upload any supporting documents?")
      .post(s"$traderUiBaseUrl/add-supporting-documents")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false") // for simplicity we do not send files in Jenkins
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postAreYouSendingASample: HttpRequestBuilder = {
    http("Are you sending a sample")
      .post(s"$traderUiBaseUrl/are-you-sending-samples")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postCouldSampleBeHazardous: HttpRequestBuilder = {
    http("Could the sample be potentially hazardous?")
      .post(s"$traderUiBaseUrl/is-sample-hazardous")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("isSampleHazardous", "false")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postReturnSample: HttpRequestBuilder = {
    http("Return Samples")
      .post(s"$traderUiBaseUrl/would-you-like-the-samples-returned")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postHaveYouFoundCommodityCode: HttpRequestBuilder = {
    http("Have you found a commodity code")
      .post(s"$traderUiBaseUrl/have-you-found-commodity-code")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postCommodityCodeDigits: HttpRequestBuilder = {
    http("Provide a commodity code")
      .post(s"$traderUiBaseUrl/provide-commodity-code")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "95065100")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postLegalChallenge: HttpRequestBuilder = {
    http("Have there been any legal challenges")
      .post(s"$traderUiBaseUrl/any-legal-challenges-classifying-goods")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postLegalChallengeDetails: HttpRequestBuilder = {
    http("Provide details of any legal challenges ")
      .post(s"$traderUiBaseUrl/provide-details-of-legal-challenges")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("legalChallengeDetails", "So many challenges")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postPreviousRulingReference: HttpRequestBuilder = {
    http("Do you have a previous ruling reference")
      .post(s"$traderUiBaseUrl/previous-ruling-reference")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postProvidePreviousRulingReference: HttpRequestBuilder = {
    http("Provide the reference number")
      .post(s"$traderUiBaseUrl/provide-previous-ruling-reference")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("btiReference", "GB12345678")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postSimilarRuling: HttpRequestBuilder = {
    http("Are there similar goods")
      .post(s"$traderUiBaseUrl/ruling-on-similar-goods")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postProvideSimilarRulingReference: HttpRequestBuilder = {
    http("Provide the reference number for a similar ruling")
      .post(s"$traderUiBaseUrl/provide-similar-ruling-reference")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "FR12345678")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postAddAnotherSimilarRuling: HttpRequestBuilder = {
    http("Do you want to add another similar ruling?")
      .post(s"$traderUiBaseUrl/add-another-similar-ruling")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("add-another-ruling-choice", "false")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postRegisterForEori: HttpRequestBuilder = {
    http("Registered Address For Eori")
      .post(s"$traderUiBaseUrl/provide-registered-eori-details")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("eori", s"$eoriNumber")
      .formParam("businessName", "Digital Tariffs Limited Company")
      .formParam("addressLine1", "Victoria Road 10")
      .formParam("townOrCity", "Shipley")
      .formParam("postcode", "LS10 6HT")
      .formParam("country", "GB")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postEnterContactDetails: HttpRequestBuilder = {
    http("Provide the contact details")
      .post(s"$traderUiBaseUrl/provide-contact-details")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("name", "Joe Bloggs")
      .formParam("email", "joe.bloggs@example.sh")
      .formParam("phoneNumber", "0123456789")
      .check(status.is(200))
      .check(saveCsrfToken)
  }

  def postCheckYourAnswers: HttpRequestBuilder = {
    http("Check Your Answers")
      .post(s"$traderUiBaseUrl/check-your-answers")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(200))
  }

  def getConfirmation: HttpRequestBuilder = {
    http("Confirmation")
      .get(s"$traderUiBaseUrl/application-complete")
      .check(status.is(200))
  }
}
