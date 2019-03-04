package uk.gov.hmrc.perftests.digitaltariffs.traderui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.digitaltariffs.CommonServices

object TraderUiRequests extends CommonServices {

  def getStartPage: HttpRequestBuilder = {
    http("Get start")
      .get(traderUiBaseUrl)
      .check(status.is(200))
      .check(currentLocation.is(traderUiBaseUrl))
  }

  def getBeforeYouStart: HttpRequestBuilder = {
    http("Before You Start - GET")
      .get(s"$traderUiBaseUrl/beforeYouStart")
      .check(css("input[name='csrfToken']", "value").saveAs("csrfToken"))
      .check(status.is(200))
  }

  def postBeforeYouStart: HttpRequestBuilder = {
    http("Before You Start - POST")
      .post(s"$traderUiBaseUrl/beforeYouStart")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(200))
  }

  def postRegisterForEori: HttpRequestBuilder = {
    http("Registered Address For Eori - POST")
      .post(s"$traderUiBaseUrl/registeredAddressForEori")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("field1", "Digital Tariffs Limited Company")
      .formParam("field2", "Victoria Road 10")
      .formParam("field3", "Shipley")
      .formParam("field4", "LS10 6HT")
      .formParam("field5", "UK")
      .check(status.is(200))
  }

  def postEnterContactDetails: HttpRequestBuilder = {
    http("Enter Contact Details - POST")
      .post(s"$traderUiBaseUrl/enterContactDetails")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("field1", "Joe Bloggs")
      .formParam("field2", "joe.bloggs@example.sh")
      .formParam("field3", "0123456789")
      .check(status.is(200))
  }

  def postWhichBestDescribesYou: HttpRequestBuilder = {
    http("Which Best Describes You")
      .post(s"$traderUiBaseUrl/whichBestDescribesYou")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "businessOwner")
      .check(status.is(200))
  }

  def postSelectApplicationType: HttpRequestBuilder = {
    http("Select Application Type")
      .post(s"$traderUiBaseUrl/selectApplicationType")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "newCommodity")
      .check(status.is(200))
  }

  def postAcceptItemInfoList: HttpRequestBuilder = {
    http("Accept Item Information List")
      .post(s"$traderUiBaseUrl/acceptItemInformationList")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(200))
  }

  def postInformationAboutYourItem: HttpRequestBuilder = {
    http("Information About Your Item")
      .post(s"$traderUiBaseUrl/informationAboutYourItem")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
  }

  def postConfidentialInformation: HttpRequestBuilder = {
    http("Confidential Information")
      .post(s"$traderUiBaseUrl/confidentialInformation")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("field1", "This is a secret application")
      .check(status.is(200))
  }

  def postDescribeYourItem: HttpRequestBuilder = {
    http("Describe Your Item")
      .post(s"$traderUiBaseUrl/describeYourItem")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("field1", "tennis racket")
      .formParam("field2", "Wilson tennis racket 100 square inches head")
      .check(status.is(200))
  }

  def postSupportingMaterialFileList: HttpRequestBuilder = {
    http("Supporting Material File List")
      .post(s"$traderUiBaseUrl/supportingMaterialFileList")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("add-file-choice", "false") // for simplicity we do not send files in Jenkins
      .check(status.is(200))
  }
  
  def postCommodityCodeBestMatch: HttpRequestBuilder = {
    http("Commodity Code Best Match")
      .post(s"$traderUiBaseUrl/commodityCodeBestMatch")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
  }

  def postCommodityCodeDigits: HttpRequestBuilder = {
    http("Commodity Code Digits")
      .post(s"$traderUiBaseUrl/commodityCodeDigits")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "95065100")
      .check(status.is(200))
  }

  def postWhenToSendSample: HttpRequestBuilder = {
    http("When To Send Sample")
      .post(s"$traderUiBaseUrl/whenToSendSample")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
  }

  def postReturnSample: HttpRequestBuilder = {
    http("Return Samples")
      .post(s"$traderUiBaseUrl/returnSamples")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "noDontReturnSamples") // TODO: change it to "false" if this will be refactored
      .check(status.is(200))
  }

  def postSimilarItemCommodityCode: HttpRequestBuilder = {
    http("Similar Item Commodity Code")
      .post(s"$traderUiBaseUrl/similarItemCommodityCode")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false")
      .check(status.is(200))
  }

  def postLegalChallenge: HttpRequestBuilder = {
    http("Legal Challenge")
      .post(s"$traderUiBaseUrl/legalChallenge")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "false")
      .check(status.is(200))
  }

  def postSupportingInformation: HttpRequestBuilder = {
    http("Supporting Information")
      .post(s"$traderUiBaseUrl/supportingInformation")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "true")
      .check(status.is(200))
  }

  def postSupportingInformationDetails: HttpRequestBuilder = {
    http("Supporting Information Details")
      .post(s"$traderUiBaseUrl/supportingInformationDetails")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("value", "All these rackets are made of aluminium")
      .check(status.is(200))
  }

  def postCheckYourAnswers: HttpRequestBuilder = {
    http("Check Your Answers")
      .post(s"$traderUiBaseUrl/check-your-answers")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(200))
  }

  def postDeclaration: HttpRequestBuilder = {
    http("Declaration")
      .post(s"$traderUiBaseUrl/declaration")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(200))
  }

}
