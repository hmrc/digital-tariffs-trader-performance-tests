package uk.gov.hmrc.perftests.digitaltariffs.traderui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.digitaltariffs.DigitalTariffsPerformanceTestRunner

object TraderUiRequests extends DigitalTariffsPerformanceTestRunner {

  private val homePage = s"$traderUiBaseUrl/applications-and-rulings?enableTrackingConsent=true"

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
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("goodsName", "Snow man jacket")
      .check(status.is(200))
  }

  def getGoodsDescription: HttpRequestBuilder = {
    http("Provide a detailed description")
      .get(s"$traderUiBaseUrl/provide-goods-description")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("goodsDescription", "Snow man jacket in black colour")
      .check(status.is(200))
  }

  def getConfidentialInfo: HttpRequestBuilder = {
    http("Do you want to add any confidential information")
      .get(s"$traderUiBaseUrl/add-confidential-information")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
  }

  def getProvideConfidentialInfo: HttpRequestBuilder = {
    http("Provide any confidential information")
      .get(s"$traderUiBaseUrl/provide-confidential-information")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("confidentialInformation", "I have used colour to pain your snow man")
      .check(status.is(200))
  }

  def getUploadSupportingDocument: HttpRequestBuilder = {
    http("Do you want to upload any supporting documents?")
      .get(s"$traderUiBaseUrl/add-supporting-documents")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false") // for simplicity we do not send files in Jenkins
      .check(status.is(200))
  }

  def getAreYouSendingASample: HttpRequestBuilder = {
    http("Are you sending a sample")
      .get(s"$traderUiBaseUrl/are-you-sending-samples")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
  }

  def getCouldSampleBeHazardous: HttpRequestBuilder = {
    http("Could the sample be potentially hazardous?")
      .get(s"$traderUiBaseUrl/is-sample-hazardous")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false")
      .check(status.is(200))
  }

  def getReturnSample: HttpRequestBuilder = {
    http("Return Samples")
      .get(s"$traderUiBaseUrl/would-you-like-the-samples-returned")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false")
      .check(status.is(200))
  }

  def getHaveYouFoundCommodityCode: HttpRequestBuilder = {
    http("Have you found a commodity code")
      .get(s"$traderUiBaseUrl/have-you-found-commodity-code")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
  }

  def getCommodityCodeDigits: HttpRequestBuilder = {
    http("Provide a commodity code")
      .get(s"$traderUiBaseUrl/provide-commodity-code")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "95065100")
      .check(status.is(200))
  }

  def getLegalChallenge: HttpRequestBuilder = {
    http("Have there been any legal challenges")
      .get(s"$traderUiBaseUrl/any-legal-challenges-classifying-goods")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
  }

  def getLegalChallengeDetails: HttpRequestBuilder = {
    http("Provide details of any legal challenges ")
      .get(s"$traderUiBaseUrl/provide-details-of-legal-challenges")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("legalChallengeDetails", "So many challenges")
      .check(status.is(200))
  }

  def getPreviousRulingReference: HttpRequestBuilder = {
    http("Do you have a previous ruling reference")
      .get(s"$traderUiBaseUrl/previous-ruling-reference")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
  }

  def getProvidePreviousRulingReference: HttpRequestBuilder = {
    http("Provide the reference number")
      .get(s"$traderUiBaseUrl/provide-previous-ruling-reference")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("btiReference", "GB12345678")
      .check(status.is(200))
  }

  def getSimilarRuling: HttpRequestBuilder = {
    http("Are there similar goods")
      .get(s"$traderUiBaseUrl/ruling-on-similar-goods")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
  }

  def getProvideSimilarRulingReference: HttpRequestBuilder = {
    http("Provide the reference number for a similar ruling")
      .get(s"$traderUiBaseUrl/provide-similar-ruling-reference")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "FR12345678")
      .check(status.is(200))
  }

  def getAddAnotherSimilarRuling: HttpRequestBuilder = {
    http("Do you want to add another similar ruling?")
      .get(s"$traderUiBaseUrl/add-another-similar-ruling")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false")
      .check(status.is(200))
  }

  def getRegisterForEori: HttpRequestBuilder = {
    http("Registered Address For Eori")
      .get(s"$traderUiBaseUrl/provide-registered-eori-details")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("businessName", "Digital Tariffs Limited Company")
      .formParam("addressLine1", "Victoria Road 10")
      .formParam("townOrCity", "Shipley")
      .formParam("postcode", "LS10 6HT")
      .formParam("country", "UK")
      .check(status.is(200))
  }

  def getEnterContactDetails: HttpRequestBuilder = {
    http("Provide the contact details")
      .get(s"$traderUiBaseUrl/provide-contact-details")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("name", "Joe Bloggs")
      .formParam("email", "joe.bloggs@example.sh")
      .formParam("phoneNumber", "0123456789")
      .check(status.is(200))
  }

  def getCheckYourAnswers: HttpRequestBuilder = {
    http("Check Your Answers")
      .get(s"$traderUiBaseUrl/check-your-answers")
      .check(status.is(200))
  }

  def getConfirmation: HttpRequestBuilder = {
    http("Confirmation")
      .get(s"$traderUiBaseUrl/application-complete")
      .check(status.is(200))
  }
}
