package uk.gov.hmrc.perftests.digitaltariffs.traderui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.digitaltariffs.DigitalTariffsPerformanceTestRunner

object TraderUiRequests extends DigitalTariffsPerformanceTestRunner {

  private val homePage = s"$traderUiBaseUrl/applications"

  def getStartPage: HttpRequestBuilder = {
    http("Get start")
      .get(traderUiBaseUrl)
      .check(status.is(200))
      .check(currentLocation.is(homePage))
  }

  def postImportOrExport: HttpRequestBuilder = {
    http("What do you want to do?")
      .post(s"$traderUiBaseUrl/import-or-export")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "import")
      .check(status.is(200))
  }

  def getInformationYouNeed: HttpRequestBuilder = {
    http("Information you need to apply for a ruling")
      .get(s"$traderUiBaseUrl/information-you-need")
      .check(status.is(200))
  }

  def getRegisterForEori: HttpRequestBuilder = {
    http("Registered Address For Eori - GET")
      .get(s"$traderUiBaseUrl/registered-address-for-eori")
      .check(css("input[name='csrfToken']", "value").saveAs("csrfToken"))
      .check(status.is(200))
  }

  def postRegisterForEori: HttpRequestBuilder = {
    http("Registered Address For Eori - POST")
      .post(s"$traderUiBaseUrl/registered-address-for-eori")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("eori", eoriNumber)
      .formParam("businessName", "Digital Tariffs Limited Company")
      .formParam("addressLine1", "Victoria Road 10")
      .formParam("townOrCity", "Shipley")
      .formParam("postcode", "LS10 6HT")
      .formParam("country", "UK")
      .check(status.is(200))
  }

  def postEnterContactDetails: HttpRequestBuilder = {
    http("Enter Contact Details - POST")
      .post(s"$traderUiBaseUrl/enter-contact-details")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("field1", "Joe Bloggs")
      .formParam("field2", "joe.bloggs@example.sh")
      .formParam("field3", "0123456789")
      .check(status.is(200))
  }

  def postWhichBestDescribesYou: HttpRequestBuilder = {
    http("Which Best Describes You")
      .post(s"$traderUiBaseUrl/who-is-this-application-for")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "businessOwner")
      .check(status.is(200))
  }

  def postSelectApplicationType: HttpRequestBuilder = {
    http("Select Application Type")
      .post(s"$traderUiBaseUrl/new-application-or-renewal")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "newCommodity")
      .check(status.is(200))
  }

  def getAcceptItemInfoList: HttpRequestBuilder = {
    http("Accept Item Information List")
      .get(s"$traderUiBaseUrl/information-you-must-provide")
      .check(status.is(200))
  }

  def postDescribeYourItem: HttpRequestBuilder = {
    http("Describe Your Item")
      .post(s"$traderUiBaseUrl/describe-your-item")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("name", "tennis racket")
      .formParam("description", "Wilson tennis racket 100 square inches head")
      .formParam("confidentialInformation", "Top secret development")
      .check(status.is(200))
  }

  def postSupportingMaterialFileList: HttpRequestBuilder = {
    http("Supporting Material File List")
      .post(s"$traderUiBaseUrl/do-you-have-supporting-documents")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("add-file-choice", "false") // for simplicity we do not send files in Jenkins
      .check(status.is(200))
  }
  
  def postCommodityCodeBestMatch: HttpRequestBuilder = {
    http("Commodity Code Best Match")
      .post(s"$traderUiBaseUrl/have-you-found-commodity-code")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
  }

  def postCommodityCodeDigits: HttpRequestBuilder = {
    http("Commodity Code Digits")
      .post(s"$traderUiBaseUrl/what-is-the-commodity-code")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "95065100")
      .check(status.is(200))
  }

  def postWhenToSendSample: HttpRequestBuilder = {
    http("When To Send Sample")
      .post(s"$traderUiBaseUrl/are-you-sending-samples")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
  }

  def postReturnSample: HttpRequestBuilder = {
    http("Return Samples")
      .post(s"$traderUiBaseUrl/would-you-like-the-samples-returned")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "noDontReturnSamples") // TODO: change it to "false" if this will be refactored
      .check(status.is(200))
  }

  def postSimilarItemCommodityCode: HttpRequestBuilder = {
    http("Similar Item Commodity Code")
      .post(s"$traderUiBaseUrl/similar-item-bti-ruling")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false")
      .check(status.is(200))
  }

  def postLegalChallenge: HttpRequestBuilder = {
    http("Legal Challenge")
      .post(s"$traderUiBaseUrl/any-problems-classifying-item")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false")
      .check(status.is(200))
  }

  def postSupportingInformation: HttpRequestBuilder = {
    http("Supporting Information")
      .post(s"$traderUiBaseUrl/any-other-supporting-information")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
  }

  def postSupportingInformationDetails: HttpRequestBuilder = {
    http("Supporting Information Details")
      .post(s"$traderUiBaseUrl/what-is-the-information-you-want-to-provide")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "All these rackets are made of aluminium")
      .check(status.is(200))
  }

  def getCheckYourAnswers: HttpRequestBuilder = {
    http("Check Your Answers")
      .get(s"$traderUiBaseUrl/check-your-answers")
      .check(status.is(200))
  }

  def getDeclaration: HttpRequestBuilder = {
    http("Declaration - GET")
      .get(s"$traderUiBaseUrl/apply-for-a-ruling")
      .check(status.is(200))
  }

  def postDeclaration: HttpRequestBuilder = {
    http("Declaration - POST")
      .post(s"$traderUiBaseUrl/apply-for-a-ruling")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(200))
  }

  def getConfirmation: HttpRequestBuilder = {
    http("Confirmation")
      .get(s"$traderUiBaseUrl/application-complete")
      .check(status.is(200))
  }

}
