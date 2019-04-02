package uk.gov.hmrc.perftests.digitaltariffs.adviceui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.digitaltariffs.DigitalTariffsPerformanceTestRunner

object AdviceUiRequests extends DigitalTariffsPerformanceTestRunner {

  private val homePage = s"$adviceUiBaseUrl"

  def getStartPage: HttpRequestBuilder = {
    http("Before you start")
      .get(adviceUiBaseUrl)
      .check(status.is(200))
      .check(currentLocation.is(homePage))
  }

  def getContactDetails: HttpRequestBuilder = {
    http("Contact Details - GET")
      .get(s"$adviceUiBaseUrl/contact-details")
      .check(css("input[name='csrfToken']", "value").saveAs("csrfToken"))
      .check(status.is(200))
  }

  def postContactDetails: HttpRequestBuilder = {
    http("Contact Details - POST")
      .post(s"$adviceUiBaseUrl/contact-details")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("full-name", "Alfredo Alfredi")
      .formParam("email", "me@me.me")
      .check(status.is(200))
  }

  def postGoodDetails: HttpRequestBuilder = {
    http("Good Details")
      .post(s"$adviceUiBaseUrl/good-details")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("item-name", "cocaine")
      .formParam("description", "Item imported from Colombia, but for medical purpose only")
      .check(status.is(200))
  }

  def postSupportingDocuments: HttpRequestBuilder = {
    http("Supporting Documents")
      .post(s"$adviceUiBaseUrl/supporting-documents")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("state", "false") // for simplicity we do not send files in Jenkins
      .check(status.is(200))
  }

  def postSupportingInformation: HttpRequestBuilder = {
    http("Supporting Information")
      .post(s"$adviceUiBaseUrl/supporting-information")
      .formParam("csrfToken", s"$${csrfToken}")
      .formParam("state", "false")
      .check(status.is(200))
  }

  def checkYourAnswers: HttpRequestBuilder = {
    http("Check Your Answers")
      .post(s"$adviceUiBaseUrl/check-your-answers")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(200))
      .check(currentLocation.is(s"$adviceUiBaseUrl/confirmation"))
  }

}
