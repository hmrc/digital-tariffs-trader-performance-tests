package simulation

import io.gatling.core.Predef.{checkBuilder2Check, findCheckBuilder2ValidatorCheckBuilder, stringToExpression, value2Expression}
import io.gatling.http.Predef.{http, status}
import io.gatling.http.request.builder.HttpRequestBuilder
import simulation.BindingTariffSteps.bindingTariffSession.csrfTokenVariable

import scala.util.{Failure, Success, Try}

object BindingTariffSteps {
//  extends ProcessesResponses {

  val baseURL = "http://localhost:9582"
  private final val csrfTokenName: String = "csrfToken"
  private final val csrfPattern: String = s"""<input type="hidden" name="$csrfTokenName" value="([^"]+)""""

  object bindingTariffSession {
    final val csrfTokenVariable: String = "csrfTokenSessionValue"
  }

  val headers_0 = Map(
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-GB,en-US;q=0.9,en;q=0.8",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_2 = Map(
    "Accept" -> "*/*",
    "Connection" -> "Keep-Alive",
    "User-Agent" -> "SXL/3.1")

  val headers_3 = Map(
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-GB,en-US;q=0.9,en;q=0.8",
    "Origin" -> "http://localhost:9949",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_5 = Map(
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-GB,en-US;q=0.9,en;q=0.8",
    "Origin" -> "http://localhost:9582",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_16 = Map(
    "Accept" -> "*/*",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-GB,en-US;q=0.9,en;q=0.8",
    "Content-Type" -> "multipart/form-data; boundary=----WebKitFormBoundaryt974rnChkrqCazYq",
    "Origin" -> "http://localhost:9582",
    "X-Requested-With" -> "XMLHttpRequest")

  val uri2 = "localhost"

  val bindingTariffLandingPage: HttpRequestBuilder = {
    http("Landing_Page")
      .get(s"$baseURL/binding-tariff-application": String)
      .headers(headers_0)
  }

  val beforeYouStartRedirect: HttpRequestBuilder = {
    http("Before_You_Start_Redirect")
      .get(s"$baseURL/binding-tariff-application/beforeYouStart")
      .headers(headers_0)
  }

  val authWizard: HttpRequestBuilder = {
    http("Auth_Wizard")
      .post("http://" + uri2 + ":9949/auth-login-stub/gg-sign-in")
      .headers(headers_3)
      .formParam("csrfToken", "you-do-not-need-this-anymore")
      .formParam("authorityId", "")
      .formParam("gatewayToken", "")
      .formParam("redirectionUrl", "http://localhost:9582/binding-tariff-application")
      .formParam("credentialStrength", "weak")
      .formParam("confidenceLevel", "50")
      .formParam("affinityGroup", "Agent")
      .formParam("usersName", "")
      .formParam("email", "user@test.com")
      .formParam("credentialRole", "User")
      .formParam("oauthTokens.accessToken", "")
      .formParam("oauthTokens.refreshToken", "")
      .formParam("oauthTokens.idToken", "")
      .formParam("nino", "")
      .formParam("groupIdentifier", "")
      .formParam("agent.agentId", "")
      .formParam("agent.agentCode", "")
      .formParam("agent.agentFriendlyName", "")
      .formParam("unreadMessageCount", "")
      .formParam("mdtp.sessionId", "")
      .formParam("mdtp.deviceId", "")
      .formParam("presets-dropdown", "IR-SA")
      .formParam("enrolment[0].name", "HMRC-CUS-ORG")
      .formParam("enrolment[0].taxIdentifier[0].name", "")
      .formParam("enrolment[0].taxIdentifier[0].value", "")
      .formParam("enrolment[0].state", "Activated")
      .formParam("enrolment[1].name", "")
      .formParam("enrolment[1].taxIdentifier[0].name", "")
      .formParam("enrolment[1].taxIdentifier[0].value", "")
      .formParam("enrolment[1].state", "Activated")
      .formParam("enrolment[2].name", "")
      .formParam("enrolment[2].taxIdentifier[0].name", "")
      .formParam("enrolment[2].taxIdentifier[0].value", "")
      .formParam("enrolment[2].state", "Activated")
      .formParam("enrolment[3].name", "")
      .formParam("enrolment[3].taxIdentifier[0].name", "")
      .formParam("enrolment[3].taxIdentifier[0].value", "")
      .formParam("enrolment[3].state", "Activated")
      .formParam("itmp.givenName", "")
      .formParam("itmp.middleName", "")
      .formParam("itmp.familyName", "")
      .formParam("itmp.dateOfBirth", "")
      .formParam("itmp.address.line1", "")
      .formParam("itmp.address.line2", "")
      .formParam("itmp.address.line3", "")
      .formParam("itmp.address.line4", "")
      .formParam("itmp.address.line5", "")
      .formParam("itmp.address.postCode", "")
      .formParam("itmp.address.countryName", "")
      .formParam("itmp.address.countryCode", "")
  }

  val redirectToBeforeYouStart: HttpRequestBuilder = {
    http("Redirect_To_Before_You_Start")
      .get(s"$baseURL/binding-tariff-application/beforeYouStart")
      .headers(headers_0)
      .check(setInSessionVariable(csrfTokenVariable, extractKeyByRegex(csrfPattern)))
  }





}
