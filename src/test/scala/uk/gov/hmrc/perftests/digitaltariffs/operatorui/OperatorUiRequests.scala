package uk.gov.hmrc.perftests.digitaltariffs.operatorui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.digitaltariffs.DigitalTariffsPerformanceTestRunner

object OperatorUiRequests extends DigitalTariffsPerformanceTestRunner {

  def getStartPage: HttpRequestBuilder = {
    http("Home Page")
      .get(operatorUiBaseUrl)
      .check(currentLocation.is(operatorUiBaseUrl + "/queues/my-cases"))
      .check(status.is(200))
  }

  def getGatewayQueue: HttpRequestBuilder = {
    http("Gateway Queue")
      .get(s"$operatorUiBaseUrl/queues/gateway")
      .disableFollowRedirect
      .check(status.is(200))
  }

  def getCarsQueue: HttpRequestBuilder = {
    http("Cars Queue")
      .get(s"$operatorUiBaseUrl/queues/cars")
      .disableFollowRedirect
      .check(status.is(200))
  }

  def getElmQueue: HttpRequestBuilder = {
    http("Elm Queue")
      .get(s"$operatorUiBaseUrl/queues/elm")
      .disableFollowRedirect
      .check(status.is(200))
  }

  def getActQueue: HttpRequestBuilder = {
    http("ACT Queue")
      .get(s"$operatorUiBaseUrl/queues/act")
      .disableFollowRedirect
      .check(status.is(200))
  }

  def getCapQueue: HttpRequestBuilder = {
    http("CAP Queue")
      .get(s"$operatorUiBaseUrl/queues/cap")
      .disableFollowRedirect
      .check(status.is(200))
  }

  def getMyCases: HttpRequestBuilder = {
    http("My cases")
      .get(s"$operatorUiBaseUrl/queues/my-cases")
      .disableFollowRedirect
      .check(status.is(200))
  }

  def getCaseTraderDetails: HttpRequestBuilder = {
    http(s"View Case $caseReference Trader Details")
      .get(s"$operatorUiBaseUrl/cases/$caseReference")
      .disableFollowRedirect
      .check(status.is(200))
  }

  def getCaseApplicationDetails: HttpRequestBuilder = {
    http(s"View Case $caseReference Application Details")
      .get(s"$operatorUiBaseUrl/cases/$caseReference/application")
      .disableFollowRedirect
      .check(status.is(200))
  }

  def getCaseAttachments: HttpRequestBuilder = {
    http(s"View Case $caseReference Attachments")
      .get(s"$operatorUiBaseUrl/cases/$caseReference/attachments")
      .disableFollowRedirect
      .check(status.is(200))
  }

  def getCaseActivity: HttpRequestBuilder = {
    http(s"View Case $caseReference Activity")
      .get(s"$operatorUiBaseUrl/cases/$caseReference/activity")
      .disableFollowRedirect
      .check(status.is(200))
  }

  def getCaseKeywords: HttpRequestBuilder = {
    http(s"View Case $caseReference Keywords")
      .get(s"$operatorUiBaseUrl/cases/$caseReference/keywords")
      .disableFollowRedirect
      .check(status.is(200))
  }

  def getCaseRuling: HttpRequestBuilder = {
    http(s"View Case $caseReference Ruling")
      .get(s"$operatorUiBaseUrl/cases/$caseReference/ruling")
      .disableFollowRedirect
      .check(status.is(200))
  }

}
