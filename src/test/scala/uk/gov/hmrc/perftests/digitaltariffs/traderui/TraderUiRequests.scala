/*
 * Copyright 2024 HM Revenue & Customs
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
import io.gatling.core.check.CheckBuilder
import io.gatling.core.check.regex.RegexCheckType
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import io.netty.handler.codec.http.HttpResponseStatus
import uk.gov.hmrc.perftests.digitaltariffs.Configuration

object TraderUiRequests extends Configuration {

  private val homePageUrl                       = "/advance-tariff-application/applications-and-rulings"
  private val provideGoodsDescriptionUrl        = "/advance-tariff-application/provide-goods-description"
  private val addConfidentialInformationUrl     = "/advance-tariff-application/add-confidential-information"
  private val provideConfidentialInformationUrl = "/advance-tariff-application/provide-confidential-information"
  private val addSupportingDocumentUrl          = "/advance-tariff-application/add-supporting-documents"
  private val areYouSendingSamplesUrl           = "/advance-tariff-application/are-you-sending-samples"
  private val isSampleHazardousUrl              = "/advance-tariff-application/is-sample-hazardous"
  private val sampleReturnUrl                   = "/advance-tariff-application/would-you-like-the-samples-returned"
  private val commodityCodeUrl                  = "/advance-tariff-application/have-you-found-commodity-code"
  private val provideCommodityCodeUrl           = "/advance-tariff-application/provide-commodity-code"
  private val classifyingGoodsUrl               = "/advance-tariff-application/any-legal-challenges-classifying-goods"
  private val legalDetailsUrl                   = "/advance-tariff-application/provide-details-of-legal-challenges"
  private val previousRulingUrl                 = "/advance-tariff-application/previous-ruling-reference"
  private val providePreviousRulingUrl          = "/advance-tariff-application/provide-previous-ruling-reference"
  private val similarRulingUrl                  = "/advance-tariff-application/ruling-on-similar-goods"
  private val provideSimilarRulingUrl           = "/advance-tariff-application/provide-similar-ruling-reference"
  private val addAnotherSimilarRulingUrl        = "/advance-tariff-application/add-another-similar-ruling"
  private val provideEoriUrl                    = "/advance-tariff-application/provide-registered-eori-details"
  private val provideContactUrl                 = "/advance-tariff-application/provide-contact-details"
  private val checkAnswersUrl                   = "/advance-tariff-application/check-your-answers"
  private val applicationCompleteUrl            = "/advance-tariff-application/application-complete"

  def saveCsrfToken: CheckBuilder[RegexCheckType, String, String] =
    regex(_ => csrfPattern).saveAs("csrfToken")

  private val csrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)"""

  def getStartPage: HttpRequestBuilder =
    http("GET Entrypoint")
      .get(traderUiBaseUrl)
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(homePageUrl))

  def getInformationYouNeed: HttpRequestBuilder =
    http("GET Information you need to apply for a ruling")
      .get(s"$traderUiBaseUrl/information-you-need")
      .check(status.is(HttpResponseStatus.OK.code()))

  def getInformationMadePublic: HttpRequestBuilder =
    http("GET Some of the information you provide")
      .get(s"$traderUiBaseUrl/information-may-be-made-public")
      .check(status.is(HttpResponseStatus.OK.code()))

  def getHowWeContactYou: HttpRequestBuilder =
    http("GET How we contact you about your application")
      .get(s"$traderUiBaseUrl/how-we-contact-you-about-your-application")
      .check(status.is(HttpResponseStatus.OK.code()))

  def getGoodsName: HttpRequestBuilder =
    http("GET Provide a name for the goods")
      .get(s"$traderUiBaseUrl/provide-goods-name")
      .check(status.is(HttpResponseStatus.OK.code()))
      .check(saveCsrfToken)

  def postGoodsName: HttpRequestBuilder =
    http("POST Provide a name for the goods")
      .post(s"$traderUiBaseUrl/provide-goods-name")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("goodsName", "Snow man jacket")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(provideGoodsDescriptionUrl))

  def postGoodsDescription: HttpRequestBuilder =
    http("POST Provide a detailed description")
      .post(s"$traderUiBaseUrl/provide-goods-description")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("goodsDescription", "Snow man jacket in black colour")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(addConfidentialInformationUrl))

  def postConfidentialInfo: HttpRequestBuilder =
    http("POST Do you want to add any confidential information")
      .post(s"$traderUiBaseUrl/add-confidential-information")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(provideConfidentialInformationUrl))

  def postProvideConfidentialInfo: HttpRequestBuilder =
    http("POST Provide any confidential information")
      .post(s"$traderUiBaseUrl/provide-confidential-information")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("confidentialInformation", "I have used colour to pain your snow man")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(addSupportingDocumentUrl))

  def postUploadSupportingDocument: HttpRequestBuilder =
    http("POST Do you want to upload any supporting documents?")
      .post(s"$traderUiBaseUrl/add-supporting-documents")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false") // for simplicity we do not send files in Jenkins
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(areYouSendingSamplesUrl))

  def postAreYouSendingASample: HttpRequestBuilder =
    http("POST Are you sending a sample")
      .post(s"$traderUiBaseUrl/are-you-sending-samples")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(isSampleHazardousUrl))

  def postCouldSampleBeHazardous: HttpRequestBuilder =
    http("POST Could the sample be potentially hazardous?")
      .post(s"$traderUiBaseUrl/is-sample-hazardous")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("isSampleHazardous", "false")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(sampleReturnUrl))

  def postReturnSample: HttpRequestBuilder =
    http("POST Return Samples")
      .post(s"$traderUiBaseUrl/would-you-like-the-samples-returned")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(commodityCodeUrl))

  def postHaveYouFoundCommodityCode: HttpRequestBuilder =
    http("POST Have you found a commodity code")
      .post(s"$traderUiBaseUrl/have-you-found-commodity-code")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(provideCommodityCodeUrl))

  def postCommodityCodeDigits: HttpRequestBuilder =
    http("POST Provide a commodity code")
      .post(s"$traderUiBaseUrl/provide-commodity-code")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "95065100")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(classifyingGoodsUrl))

  def postLegalChallenge: HttpRequestBuilder =
    http("POST Have there been any legal challenges")
      .post(s"$traderUiBaseUrl/any-legal-challenges-classifying-goods")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(legalDetailsUrl))

  def postLegalChallengeDetails: HttpRequestBuilder =
    http("POST Provide details of any legal challenges ")
      .post(s"$traderUiBaseUrl/provide-details-of-legal-challenges")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("legalChallengeDetails", "So many challenges")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(previousRulingUrl))

  def postPreviousRulingReference: HttpRequestBuilder =
    http("POST Do you have a previous ruling reference")
      .post(s"$traderUiBaseUrl/previous-ruling-reference")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(providePreviousRulingUrl))

  def postProvidePreviousRulingReference: HttpRequestBuilder =
    http("POST Provide the reference number")
      .post(s"$traderUiBaseUrl/provide-previous-ruling-reference")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("btiReference", "GB12345678")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(similarRulingUrl))

  def postSimilarRuling: HttpRequestBuilder =
    http("POST Are there similar goods")
      .post(s"$traderUiBaseUrl/ruling-on-similar-goods")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(provideSimilarRulingUrl))

  def postProvideSimilarRulingReference: HttpRequestBuilder =
    http("POST Provide the reference number for a similar ruling")
      .post(s"$traderUiBaseUrl/provide-similar-ruling-reference")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "FR12345678")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(addAnotherSimilarRulingUrl))

  def postAddAnotherSimilarRuling: HttpRequestBuilder =
    http("POST Do you want to add another similar ruling?")
      .post(s"$traderUiBaseUrl/add-another-similar-ruling")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("add-another-ruling-choice", "false")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(provideEoriUrl))

  def postRegisterForEori: HttpRequestBuilder =
    http("POST Registered Address For Eori")
      .post(s"$traderUiBaseUrl/provide-registered-eori-details")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("eori", s"$eoriNumber")
      .formParam("businessName", "Digital Tariffs Limited Company")
      .formParam("addressLine1", "Victoria Road 10")
      .formParam("townOrCity", "Shipley")
      .formParam("postcode", "LS10 6HT")
      .formParam("country", "GB")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(provideContactUrl))

  def postEnterContactDetails: HttpRequestBuilder =
    http("POST Provide the contact details")
      .post(s"$traderUiBaseUrl/provide-contact-details")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("name", "Joe Bloggs")
      .formParam("email", "joe.bloggs@example.sh")
      .formParam("phoneNumber", "0123456789")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(checkAnswersUrl))

  def getCheckYourAnswers: HttpRequestBuilder =
    http("GET Check Your Answers")
      .get(s"$traderUiBaseUrl/check-your-answers")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(HttpResponseStatus.OK.code()))

  def postCheckYourAnswers: HttpRequestBuilder =
    http("POST Check Your Answers")
      .post(s"$traderUiBaseUrl/check-your-answers")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(applicationCompleteUrl))

  def getConfirmation: HttpRequestBuilder =
    http("GET Confirmation")
      .get(s"$traderUiBaseUrl/application-complete")
      .check(status.is(HttpResponseStatus.OK.code()))
}
