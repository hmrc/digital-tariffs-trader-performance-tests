package simulation.recorded

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TariffClassificationHappyPath extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:9581")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png""", """.*\.map"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")

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

	val scn = scenario("TariffClassification HappyPath")
		.exec(http("request_0")
			.get(uri1 + "/phfpbpuebzrrkgrafvba-cn.tbbtyrncvf.pbz.w/")
			.headers(headers_0)
			.resources(http("request_1")
			.get(uri1 + "/42.206.58.216.ip/")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("HappyPath_0001_response.txt"))))
			.check(bodyBytes.is(RawFileBody("HappyPath_0000_response.txt"))))
		.pause(17)
		// Landing Page
		.exec(http("request_2")
			.get("/tariff-classification")
			.headers(headers_2)
			.resources(http("request_3")
			.get("http://" + uri2 + ":9043/stride-idp-stub/auth-request?SAMLRequest=fZFBb8IgGIbv%2BxUNd0rB6ZTYmmadmYkuS2132A0pnU1a6Pio2f79WJ3JvHgE3ud74WG5%2Bura4KQsNEbHiIYRCpSWpmr0R4zKYo3naJXcLUF0Let5OrijztXnoMAFKYCyznOPRsPQKbtX9tRIVebbGB2d64ET0hop2qMBR8DZplJE%2BBHYKug9pFCQ%2BUmNFm6sv0A1hKoT0vmzUJqOiKoG0gJBwSaL0S4rXnH0MBcTIRlWC3HA92wxxfOoYpjNGK2nVMzk9ODjAIPaaHBCuxixiC5wRDFjBWU8WvAJDSml7yh4uwhgvwK8Eg38%2FOQYDVZzI6ABrkWngDvJ9%2Bluy32U99Y4I02LkrMhPhbaYG1sJ9xt9nenqXA9RrnSrnHfV923cXGxj5Iyf%2BGjlH2Rb7InnJbF85L8v1Dyt7z%2BweQH&RelayState=successURL%3Dhttp%253A%252F%252Flocalhost%253A9581%252Ftariff-classification%26failureURL%3D%252Fstride%252Ffailure%253FcontinueURL%253Dhttp%2525253A%2525252F%2525252Flocalhost%2525253A9581%2525252Ftariff-classification")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("HappyPath_0003_response.txt")))))
		.pause(3)
		.exec(http("request_4")
			.get(uri1 + "/42.204.58.216.ip/")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("HappyPath_0004_response.txt"))))
		.pause(23)
		// Auth
		.exec(http("request_5")
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
			.resources(http("request_6")
			.post("http://" + uri2 + ":9041/stride/auth-response")
			.headers(headers_5)
			.formParam("SAMLResponse", "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNhbWwycDpSZXNwb25zZSBJRD0iYThkNzk1NWUtMzcyMi00ZWNhLWExYmEtYmVlMDA1OWM0YzUyIiBJc3N1ZUluc3RhbnQ9IjIwMTktMDEtMjJUMTI6MDk6NTguNTk1WiIgVmVyc2lvbj0iMi4wIiB4bWxuczpzYW1sMnA9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpwcm90b2NvbCI+PHNhbWwyOklzc3VlciB4bWxuczpzYW1sMj0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOmFzc2VydGlvbiI+aHR0cDovL2ZzLmVtYWN0ZXN0LmNvbS9hZGZzL3NlcnZpY2VzL3RydXN0PC9zYW1sMjpJc3N1ZXI+PHNhbWwycDpTdGF0dXM+PHNhbWwycDpTdGF0dXNDb2RlIFZhbHVlPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6c3RhdHVzOlN1Y2Nlc3MiLz48L3NhbWwycDpTdGF0dXM+PHNhbWwyOkFzc2VydGlvbiBWZXJzaW9uPSIyLjAiIHhtbG5zOnNhbWwyPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6YXNzZXJ0aW9uIj48c2FtbDI6SXNzdWVyPmh0dHA6Ly9mcy5lbWFjdGVzdC5jb20vYWRmcy9zZXJ2aWNlcy90cnVzdDwvc2FtbDI6SXNzdWVyPjxkczpTaWduYXR1cmUgeG1sbnM6ZHM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyMiPgo8ZHM6U2lnbmVkSW5mbz4KPGRzOkNhbm9uaWNhbGl6YXRpb25NZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDA2LzEyL3htbC1jMTRuMTEiLz4KPGRzOlNpZ25hdHVyZU1ldGhvZCBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMDQveG1sZHNpZy1tb3JlI3JzYS1zaGE1MTIiLz4KPGRzOlJlZmVyZW5jZSBVUkk9IiI+CjxkczpUcmFuc2Zvcm1zPgo8ZHM6VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnI2VudmVsb3BlZC1zaWduYXR1cmUiLz4KPGRzOlRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMTAveG1sLWV4Yy1jMTRuIyIvPgo8L2RzOlRyYW5zZm9ybXM+CjxkczpEaWdlc3RNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGVuYyNzaGEyNTYiLz4KPGRzOkRpZ2VzdFZhbHVlPjgrMXJBUXlqam1XR1BVQk83SVpnYzNNbU5GblV2UnZ3NTNORXcwMDQ2TUk9PC9kczpEaWdlc3RWYWx1ZT4KPC9kczpSZWZlcmVuY2U+CjwvZHM6U2lnbmVkSW5mbz4KPGRzOlNpZ25hdHVyZVZhbHVlPgpHV1c4dG8zM2NZdXJhTi93bGFsWDNFMGNqQkdvR1RmYmdxYzN5bndwVWpBUU5qVlA2VDZFQUdWZG1UeVVMenhEWEZaL2s4RExBbUFFCk5XNy94ek90aFpmMjZMSktYaG4vcHNsbTRnYzhmUlc2VEFzQmE3TS90UXJqOFlWejRPSEFjRjBhK2dIbjlySjlGUUl2WExzLzArbTYKM0s4WlBjdVBlblFITThXNTZHNjNJWC9lbzRvWTR2YnRaajNPZ3dXbHpIcGJkN2YyTzYzMmFCM3hkS3RvUkNESThvTEx6MTNLODZxeApyM1FCRXoxeDZadWU0Q2s1eU9uaTJqWVZpNHFock9NRHNsVkd4ZFB2K1h1MHk0czZ2MFVNYzRVVUtVQW9aNUNWRVVreVgvMURoOXh5CkhZZ09wQUcyWFp1TkxXdVJXZ0dkL3NHN3cvbEF4RzdzTHpxVEVnPT0KPC9kczpTaWduYXR1cmVWYWx1ZT4KPC9kczpTaWduYXR1cmU+PHNhbWwyOlN1YmplY3Q+PHNhbWwyOlN1YmplY3RDb25maXJtYXRpb24gTWV0aG9kPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoyLjA6Y206YmVhcmVyIj48c2FtbDI6U3ViamVjdENvbmZpcm1hdGlvbkRhdGEgSW5SZXNwb25zZVRvPSJNRFRQLTk2NWM1MjMxLWYwZGEtNGQ0OS1iZGQ1LWUzY2YzZjFjYWY2OCIgTm90T25PckFmdGVyPSIyMDE5LTAxLTIyVDEyOjA5OjU4LjU5NloiIFJlY2lwaWVudD0iaHR0cHM6Ly93d3cudGF4LnNlcnZpY2UuZ292LnVrL3N0cmlkZS9hdXRoLXJlc3BvbnNlIi8+PC9zYW1sMjpTdWJqZWN0Q29uZmlybWF0aW9uPjwvc2FtbDI6U3ViamVjdD48c2FtbDI6QXV0aG5TdGF0ZW1lbnQgU2Vzc2lvbkluZGV4PSI3YzI3OTRmNi04YjdkLTQyNjctOWExYy03N2MxZmY0NDAyY2MiLz48c2FtbDI6QXR0cmlidXRlU3RhdGVtZW50PjxzYW1sMjpBdHRyaWJ1dGUgTmFtZT0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI+PHNhbWwyOkF0dHJpYnV0ZVZhbHVlPjA8L3NhbWwyOkF0dHJpYnV0ZVZhbHVlPjwvc2FtbDI6QXR0cmlidXRlPjxzYW1sMjpBdHRyaWJ1dGUgTmFtZT0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvZ2l2ZW5uYW1lIj48c2FtbDI6QXR0cmlidXRlVmFsdWU+UGVyZm9ybWFuY2U8L3NhbWwyOkF0dHJpYnV0ZVZhbHVlPjwvc2FtbDI6QXR0cmlidXRlPjxzYW1sMjpBdHRyaWJ1dGUgTmFtZT0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvc3VybmFtZSI+PHNhbWwyOkF0dHJpYnV0ZVZhbHVlPlVzZXI8L3NhbWwyOkF0dHJpYnV0ZVZhbHVlPjwvc2FtbDI6QXR0cmlidXRlPjxzYW1sMjpBdHRyaWJ1dGUgTmFtZT0iaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvZW1haWxhZGRyZXNzIi8+PHNhbWwyOkF0dHJpYnV0ZSBOYW1lPSJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiPjxzYW1sMjpBdHRyaWJ1dGVWYWx1ZT5jbGFzc2lmaWNhdGlvbjwvc2FtbDI6QXR0cmlidXRlVmFsdWU+PC9zYW1sMjpBdHRyaWJ1dGU+PC9zYW1sMjpBdHRyaWJ1dGVTdGF0ZW1lbnQ+PC9zYW1sMjpBc3NlcnRpb24+PC9zYW1sMnA6UmVzcG9uc2U+")
			.formParam("RelayState", "successURL=http%3A%2F%2Flocalhost%3A9581%2Ftariff-classification&failureURL=%2Fstride%2Ffailure%3FcontinueURL%3Dhttp%25253A%25252F%25252Flocalhost%25253A9581%25252Ftariff-classification")))
		.pause(21)
		// Navigate To Gateway cases
		.exec(http("request_7")
			.get("/tariff-classification/queues/gateway")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("HappyPath_0007_response.txt"))))
		.pause(18)
		// Open Case
		.exec(http("request_8")
			.get("/tariff-classification/cases/1795")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("HappyPath_0008_response.txt"))))
		.pause(28)
		.exec(http("request_9")
			.get(uri1 + "/ffy.tfgngvp.pbz.w/")
			.headers(headers_0)
			.resources(http("request_10")
			.get(uri1 + "/35.23.217.172.ip/")
			.headers(headers_0)
			.check(bodyBytes.is(RawFileBody("HappyPath_0010_response.txt"))))
			.check(bodyBytes.is(RawFileBody("HappyPath_0009_response.txt"))))
		.pause(6)
		// Navigate to Application Details
		.exec(http("request_11")
			.get("/tariff-classification/cases/1795/application")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("HappyPath_0011_response.txt"))))
		.pause(26)
		// Release Case
		.exec(http("request_12")
			.get("/tariff-classification/cases/1795/release")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("HappyPath_0012_response.txt"))))
		.pause(31)
		// Choose Queue
		.exec(http("request_13")
			.post("/tariff-classification/cases/1795/release")
			.headers(headers_13)
			.formParam("csrfToken", "313082f1e4518087c0e75aefaad8ef1a19711245-1548159100855-606f6d31be5ca0f1e629e29b")
			.formParam("queue", "act")
			.check(bodyBytes.is(RawFileBody("HappyPath_0013_response.txt"))))
		.pause(21)
		// Navigate to My Cases
		.exec(http("request_14")
			.get("/tariff-classification")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("HappyPath_0014_response.txt"))))
		.pause(14)
		// Navigate to queue
		.exec(http("request_15")
			.get("/tariff-classification/queues/act")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("HappyPath_0015_response.txt"))))
		.pause(9)
		// Open case
		.exec(http("request_16")
			.get("/tariff-classification/cases/1795")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("HappyPath_0016_response.txt"))))
		.pause(11)
		// Navigate to Ruling
		.exec(http("request_17")
			.get("/tariff-classification/cases/1795/ruling")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("HappyPath_0017_response.txt"))))
		.pause(9)
		// Click Edit
		.exec(http("request_18")
			.get("/tariff-classification/cases/1795/ruling/edit")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("HappyPath_0018_response.txt"))))
		.pause(21)
		// Save Changes
		.exec(http("request_19")
			.post("/tariff-classification/cases/1795/ruling")
			.headers(headers_13)
			.formParam("csrfToken", "523651eedee095c789984d9bdf4dad8b963d86a8-1548159198411-606f6d31be5ca0f1e629e29b")
			.formParam("bindingCommodityCode", "123456")
			.formParam("goodsDescription", "test")
			.formParam("methodSearch", "test")
			.formParam("justification", "test")
			.formParam("methodCommercialDenomination", "test")
			.formParam("methodExclusion", "test")
			.check(bodyBytes.is(RawFileBody("HappyPath_0019_response.txt"))))
		.pause(10)
		// Complete Case
		.exec(http("request_20")
			.get("/tariff-classification/cases/1795/complete")
			.headers(headers_2)
			.check(bodyBytes.is(RawFileBody("HappyPath_0020_response.txt"))))
		.pause(17)
		// Confirm complete case
		.exec(http("request_21")
			.get(uri1 + "/jjj.tbbtyr-nanylgvpf.pbz.w/")
			.headers(headers_0)
			.resources(http("request_22")
			.post("/tariff-classification/cases/1795/complete")
			.headers(headers_13)
			.formParam("csrfToken", "6b0e50fb46b7e1a0f01af667f8a6914a5e0eefc8-1548159231050-606f6d31be5ca0f1e629e29b")
			.check(bodyBytes.is(RawFileBody("HappyPath_0022_response.txt"))))
			.check(bodyBytes.is(RawFileBody("HappyPath_0021_response.txt"))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}