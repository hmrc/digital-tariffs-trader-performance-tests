package uk.gov.hmrc.perftests.digitaltariffs.rulingui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.digitaltariffs.DigitalTariffsPerformanceTestRunner

object RulingUiRequests extends DigitalTariffsPerformanceTestRunner {

  private val homePage = s"$rulingUiBaseUrl"

  def getStartPage: HttpRequestBuilder = {
    http("Start Page")
      .get(rulingUiBaseUrl)
      .check(status.is(200))
      .check(currentLocation.is(homePage))
  }

  def searchPage: HttpRequestBuilder = {
    http("Search Page")
      .get(s"$rulingUiBaseUrl/search")
      .check(status.is(200))
  }

  def getQueryResultPage: HttpRequestBuilder = {
    http("Search Results")
      .get(s"$rulingUiBaseUrl/search?query=laptop")
      .check(status.is(200))
  }

}
