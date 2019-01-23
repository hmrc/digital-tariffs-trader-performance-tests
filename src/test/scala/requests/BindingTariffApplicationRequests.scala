package requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object BindingTariffApplicationRequests extends BaseRequest {

  // TODO: add baseUrl that works for any environment (local + MDTP)
  // TODO: add `pause` actions
  // TODO: add `User-Agent`

  def login = {
    ???
  }

  def start: HttpRequestBuilder = {
    http("Navigate to the landing page")
      .get("/binding-tariff-application")
      .check(status.is(200))
  }

  def beforeYouStart: HttpRequestBuilder = {
    http("Navigate to the landing page")
      .get("/binding-tariff-application")
      .check(status.is(200))
  }

}
