package simulation.recorded

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TariffClassificationHappyPath extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:9581")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png""", """.*\.map"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
		.userAgentHeader("DigitalTariffs-PerformanceTests")

	private val waitTime = 5
	private val applicationID = 1897
	private val samlResponcePattern = """SamlResponse=([^"]+)"""
	private val csrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)""""

	private def saveSamlResponse() = currentLocationRegex(samlResponcePattern).saveAs("samlResponse")
	private def saveCsrfToken() = regex(_ => csrfPattern).saveAs("csrfToken")

	val headers_0 = Map(
		"Accept" -> "*/*",
		"Connection" -> "Keep-Alive",
		"User-Agent" -> "SXL/3.1")

	val headers_2 = Map(
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_5 = Map(
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"Origin" -> "http://localhost:9043",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_13 = Map(
		"Accept-Encoding" -> "gzip, deflate, br",
		"Accept-Language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"Origin" -> "http://localhost:9581",
		"Upgrade-Insecure-Requests" -> "1")

	val uri1 = "http://http.00.h.sophosxl.net/V3/01"
	val uri2 = "localhost"

	val scn = scenario("TariffClassification_HappyPath")
		// Landing Page
		.exec(http("Landing_Page")
		.get("/tariff-classification")
		.headers(headers_2)
		//  		.check(saveSamlResponse())
		.resources(http("request_3")
		.get(s"http://" + uri2 + ":9043/stride-idp-stub/auth-request?SAMLRequest=${samlResponse}RelayState=successURL%3Dhttp%253A%252F%252Flocalhost%253A9581%252Ftariff-classification%26failureURL%3D%252Fstride%252Ffailure%253FcontinueURL%253Dhttp%2525253A%2525252F%2525252Flocalhost%2525253A9581%2525252Ftariff-classification")
		.headers(headers_2)))
		//.check(bodyBytes.is(RawFileBody("HappyPath_0003_response.txt")))))
		.pause(waitTime)
		// Auth
		.exec(http("Login")
		.post("http://" + uri2 + ":9043/stride-idp-stub/sign-in")
		.headers(headers_5)
		.formParam("RelayState", "successURL=http%3A%2F%2Flocalhost%3A9581%2Ftariff-classification&failureURL=%2Fstride%2Ffailure%3FcontinueURL%3Dhttp%25253A%25252F%25252Flocalhost%25253A9581%25252Ftariff-classification")
		.formParam("pid", "0")
		.formParam("usersGivenName", "Performance")
		.formParam("usersSurname", "User")
		.formParam("emailAddress", "")
		.formParam("status", "true")
		.formParam("signature", "valid")
		.formParam("roles", "classification")
		.check(saveSamlResponse())
		.resources(http("request_6")
			.post("http://" + uri2 + ":9041/stride/auth-response")
			.headers(headers_5)
			.formParam("SAMLResponse", "${samlResponse}")
			.formParam("RelayState", "successURL=http%3A%2F%2Flocalhost%3A9581%2Ftariff-classification&failureURL=%2Fstride%2Ffailure%3FcontinueURL%3Dhttp%25253A%25252F%25252Flocalhost%25253A9581%25252Ftariff-classification")))
		.pause(waitTime)
		// Navigate To Gateway cases
		.exec(http("Navigate_To_Gateway_Cases")
		.get("/tariff-classification/queues/gateway")
		.headers(headers_2))
		//.check(bodyBytes.is(RawFileBody("HappyPath_0007_response.txt"))))
		.pause(waitTime)
		// Open Case
		.exec(http("Select_case") //TODO : Parameter for case ID
		.get(s"/tariff-classification/cases/${applicationID}")
		.headers(headers_2))
		//.check(bodyBytes.is(RawFileBody("HappyPath_0008_response.txt"))))
		.pause(waitTime)
		// Navigate to Application Details
		.exec(http("Navigate_To_Application_Details")
		.get(s"/tariff-classification/cases/${applicationID}/application")
		.headers(headers_2))
		//.check(bodyBytes.is(RawFileBody("HappyPath_0011_response.txt"))))
		.pause(waitTime)
		// Release Case
		.exec(http("Release_Case")
		.get(s"/tariff-classification/cases/${applicationID}/release")
		.headers(headers_2)
		//.check(bodyBytes.is(RawFileBody("HappyPath_0012_response.txt")))
		.check(saveCsrfToken()))
		.pause(waitTime)
		// Choose Queue
		.exec(http("Select_Queue")
		.post(s"/tariff-classification/cases/${applicationID}/release")
		.headers(headers_13)
		.formParam("csrfToken", "${csrfToken}")
		.formParam("queue", "act"))
		//.check(bodyBytes.is(RawFileBody("HappyPath_0013_response.txt"))))
		.pause(waitTime)
		// Navigate to My Cases
		.exec(http("Navigate_To_My_Cases")
		.get("/tariff-classification")
		.headers(headers_2))
		//.check(bodyBytes.is(RawFileBody("HappyPath_0014_response.txt"))))
		.pause(waitTime)
		// Navigate to queue
		.exec(http("Navigate_To_ACT_Queue")
		.get("/tariff-classification/queues/act")
		.headers(headers_2))
		//.check(bodyBytes.is(RawFileBody("HappyPath_0015_response.txt"))))
		.pause(waitTime)
		// Open case
		.exec(http("Open_Case_From_Queue")
		.get(s"/tariff-classification/cases/${applicationID}")
		.headers(headers_2))
		//.check(bodyBytes.is(RawFileBody("HappyPath_0016_response.txt"))))
		.pause(waitTime)
		// Navigate to Ruling
		.exec(http("Navigate_To_Rulings_Tab")
		.get(s"/tariff-classification/cases/${applicationID}/ruling")
		.headers(headers_2))
		//.check(bodyBytes.is(RawFileBody("HappyPath_0017_response.txt"))))
		.pause(waitTime)
		// Click Edit
		.exec(http("Click_Edit")
		.get(s"/tariff-classification/cases/${applicationID}/ruling/edit")
		.headers(headers_2)
		//.check(bodyBytes.is(RawFileBody("HappyPath_0018_response.txt")))
		.check(saveCsrfToken()))
		.pause(waitTime)
		// Save Changes
		.exec(http("Save_Ruling_Information")
		.post(s"/tariff-classification/cases/${applicationID}/ruling")
		.headers(headers_13)
		.formParam("csrfToken", "${csrfToken}")
		.formParam("bindingCommodityCode", "123456")
		.formParam("goodsDescription", "test")
		.formParam("methodSearch", "test")
		.formParam("justification", "test")
		.formParam("methodCommercialDenomination", "test")
		.formParam("methodExclusion", "test"))
		//.check(bodyBytes.is(RawFileBody("HappyPath_0019_response.txt"))))
		.pause(waitTime)
		// Complete Case
		.exec(http("Click_Complete_Case")
		.get(s"/tariff-classification/cases/${applicationID}/complete")
		.headers(headers_2)
		//.check(bodyBytes.is(RawFileBody("HappyPath_0020_response.txt")))
		.check(saveCsrfToken()))
		.pause(waitTime)
		// Confirm complete case
		.exec(http("Confirm_Complete_Case")
		.post(s"/tariff-classification/cases/${applicationID}/complete")
		.headers(headers_13)
		.formParam("csrfToken", "${csrfToken}"))
	//.check(bodyBytes.is(RawFileBody("HappyPath_0022_response.txt")))
	//.check(bodyBytes.is(RawFileBody("HappyPath_0021_response.txt"))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}