/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.digitaltariffs.traderui

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import io.netty.handler.codec.http.HttpResponseStatus
import uk.gov.hmrc.perftests.digitaltariffs.Configuration
import uk.gov.hmrc.perftests.digitaltariffs.traderui.TraderUiRequests.saveCsrfToken

object AuthRequests extends Configuration {

  def getGovGatewaySignIn: HttpRequestBuilder =
    http("Government Gateway Sign In - GET")
      .get(authStubBaseUrl)
      .check(status.is(HttpResponseStatus.OK.code()))
      .check(saveCsrfToken)

  def postGovGatewaySignIn: HttpRequestBuilder =
    http("Government Gateway Sign In - POST")
      .post(authStubBaseUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("authorityId", "")
      .formParam("redirectionUrl", traderUiBaseUrl)
      .formParam("credentialStrength", "strong")
      .formParam("confidenceLevel", "50")
      .formParam("affinityGroup", "Individual")
      .formParam("enrolment[0].name", "HMRC-ATAR-ORG")
      .formParam("enrolment[0].taxIdentifier[0].name", "EORINumber")
      .formParam("enrolment[0].taxIdentifier[0].value", eoriNumber)
      .formParam("enrolment[0].state", "Activated")
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header("Location").is(traderUiBaseUrl))
}
