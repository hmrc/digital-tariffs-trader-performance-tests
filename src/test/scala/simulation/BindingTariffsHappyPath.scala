package simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class HappyPath extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:9582")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png""", """.*\.map"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")

	val waitTime = 5
	private val csrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)""""

	private def saveCsrfToken() = regex(_ => csrfPattern).saveAs("csrfToken")

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

	val scn = scenario("HappyPath")
		.exec(http("Landing_Page")
			.get("/binding-tariff-application")
			.headers(headers_0))
		.pause(waitTime)
		// LandingPage
		.exec(http("Before_You_Start_Redirect")
			.get("/binding-tariff-application/beforeYouStart")
			.headers(headers_0))
		.pause(waitTime)
		// AuthWizard
		.exec(http("Auth_Wizard")
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
			.formParam("itmp.address.countryCode", ""))
		.pause(waitTime)
		// WhatWouldYouLikeToDo
		.exec(http("Redirect_To_Before_You_Start")
			.get("/binding-tariff-application/beforeYouStart")
			.headers(headers_0)
  		.check(saveCsrfToken()))
		.pause(waitTime)
		// BeforeYouStart
		.exec(http("Before_You_Start_Click_Continue")
			.post("/binding-tariff-application/beforeYouStart")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// RegisteredAddressForEori
		.exec(http("Submit_Registered_Address_For_Eori")
			.post("/binding-tariff-application/registeredAddressForEori")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("field1", "Test Ltd")
			.formParam("field2", "Test Street")
			.formParam("field3", "Test Town")
			.formParam("field4", "LS119BH")
			.formParam("field5", "United Kingdom")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// ContactDetails
		.exec(http("Submit_Contact_Details")
			.post("/binding-tariff-application/enterContactDetails")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("field1", "Test Testerson")
			.formParam("field2", "test@test.com")
			.formParam("field3", "077123456")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// WhoIsTheApplicationFor
		.exec(http("Submit_User_Type")
			.post("/binding-tariff-application/whichBestDescribesYou")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("value", "businessOwner")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// NewApplication
		.exec(http("Submit_Application_Type")
			.post("/binding-tariff-application/selectApplicationType")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("value", "newCommodity")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// ProvideInformation
		.exec(http("Provide_Information_Click_Continue")
			.post("/binding-tariff-application/acceptItemInformationList")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// AnyConfidentialInformation
		.exec(http("Any_Information_Confidential_Yes")
			.post("/binding-tariff-application/informationAboutYourItem")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("value", "yesIHaveInfo")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// ConfidentialInformation
		.exec(http("Submit_Confidential_Information")
			.post("/binding-tariff-application/confidentialInformation")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("field1", "This is confidential information.")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// DescribeItem
		.exec(http("Submit_Item_Description")
			.post("/binding-tariff-application/describeYourItem")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("field1", "Test Item")
			.formParam("field2", "Test item description.")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// AnyFilesToUpload
		.exec(http("Any_Filoes_To_Upload_No")
			.post("/binding-tariff-application/askForUploadSupportingMaterial")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("value", "false")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// FileUpload
		/*exec(http("File_Upload")
			.post("/binding-tariff-application/uploadSupportingMaterialMultiple")
			.headers(headers_16)
			.body(RawFileBody("HappyPath_0016_request.txt"))
			.resources(http("request_17")
			.get("/binding-tariff-application/commodityCodeBestMatch")
			.headers(headers_0)))
			//.check(saveCsrfToken()))
		.pause(waitTime)*/
		// FoundCommodityCode
		.exec(http("Found_Similar_Commodity_Code_Yes")
			.post("/binding-tariff-application/commodityCodeBestMatch")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("value", "yesFoundCommodityCode")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// CommodityCode
		.exec(http("Submit_Similar_Commodity_Code")
			.post("/binding-tariff-application/commodityCodeDigits")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("value", "GB123456")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// Samples
		.exec(http("Sending_Sample_Yes")
			.post("/binding-tariff-application/whenToSendSample")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("value", "yesProvideSample")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// ReturnSample
		.exec(http("Sample_Return_Yes")
			.post("/binding-tariff-application/returnSamples")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("value", "yesReturnSamples")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// SimilarItem
		.exec(http("Similar_Item_Details_Yes")
			.post("/binding-tariff-application/similarItemCommodityCode")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("value", "yesAwareSimilarCode")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// SimilarItemDetails
		.exec(http("Submit_Similar_Items_Details")
			.post("/binding-tariff-application/commodityCodeRulingReference")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("value", "Similar item details.")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// AnyProblems
		.exec(http("Aware_Of_Any_Problems_Yes")
			.post("/binding-tariff-application/legalChallenge")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("value", "yesLegalChallenge")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// WhatProblems
		.exec(http("Submit_Problem_Details")
			.post("/binding-tariff-application/legalChallengeDetails")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("value", "Big problems.")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// AnyOtherInformation
		.exec(http("Supporting_Information_Yes")
			.post("/binding-tariff-application/supportingInformation")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("value", "yesInformation")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// OtherInformation
		.exec(http("Submit_Supporting_Information")
			.post("/binding-tariff-application/supportingInformationDetails")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.formParam("value", "This is supporting information.")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// CheckAnmswers
		.exec(http("Submit_Answers")
			.post("/binding-tariff-application/check-your-answers")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}")
			.check(saveCsrfToken()))
		.pause(waitTime)
		// Declaration
		.exec(http("Submit_Declaration")
			.post("/binding-tariff-application/declaration")
			.headers(headers_5)
			.formParam("csrfToken", "${csrfToken}"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
