package uk.gov.hmrc.perftests.digitaltariffs.operatorui

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object StrideAuthRequests {

  private val baseAdminUrl = "https://admin.staging.tax.service.gov.uk"
  private val baseOperatorUiUrl = s"$baseAdminUrl/tariff-classification"

  private val relayStatePattern = """<input type="hidden" id="RelayState" name="RelayState" value="([^"]+)""""
  private val samlResponsePattern = """<input type="hidden" name="SAMLResponse" value="([^"]+)""""
  private val formUrlPattern = """<form action="([^"]+)""""

  private def savePageItem(name: String, pattern: String) = regex(_ => pattern).saveAs(name)

  val getProtectedPageNoSession =
    http("Stride Auth - [GET] page without session")
      .get(s"$baseOperatorUiUrl")
      .disableFollowRedirect
      .check(status.is(303))
      .check(header("location").saveAs("protectedPageRedirect"))


  val getStrideSignIn =
    http("Stride Auth - [GET] redirect to STRIDE Auth")
      .get(s"$${protectedPageRedirect}")
      .disableFollowRedirect
      .check(status.is(303))
      .check(header("location").saveAs("authRequestRedirect"))


  val getIdpSignInPage =
    http("Stride Auth - [GET] redirect to IdP login")
      .get(s"$${authRequestRedirect}")
      .check(status.is(200))
      .check(savePageItem("relayState", relayStatePattern))
      .check(savePageItem("formUrl", formUrlPattern))


  val postIdpSignInPage =
    http("Stride Auth - [POST] IdP login form")
      .post(s"$baseAdminUrl$${formUrl}")
      .disableFollowRedirect
      .formParam("RelayState", s"$${relayState}")
      .formParam("pid", "12345")
      .formParam("status", true)
      .formParam("signature", "valid")
      .formParam("roles", "classification")
      .check(status.is(303))
      .check(header("location").saveAs("signInRedirect"))


  val getSignInRedirect =
    http("Stride Auth - [GET] page w/ JS redirect to STRIDE Auth")
      .get(s"$baseAdminUrl$${signInRedirect}")
      .check(status.is(200))
      .check(savePageItem("formUrl", formUrlPattern))
      .check(savePageItem("samlResponse", samlResponsePattern))
      .check(savePageItem("relayState", relayStatePattern))


  val postIdpResponseToStride =
    http("Stride Auth - [POST] IdP response to STRIDE Auth")
      .post(s"$${formUrl}")
      .disableFollowRedirect
      .formParam("SAMLResponse", s"$${samlResponse}")
      .formParam("RelayState", s"$${relayState}")
      .check(status.is(303))
      .check(header("location").saveAs("loginRedirect"))

}
