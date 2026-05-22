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
import io.gatling.core.session.StaticValueExpression

object AuthRequests extends Configuration {

  def getGovGatewaySignIn: HttpRequestBuilder =
    http("Government Gateway Sign In - GET")
      .get(authStubBaseUrl)
      .check(status.is(HttpResponseStatus.OK.code()))
      .check(saveCsrfToken)

  def postGovGatewaySignIn: HttpRequestBuilder =
    http("Government Gateway Sign In - POST")
      .post(authStubBaseUrl)
      .formParam("csrfToken", session => session("csrfToken").as[String])
      .formParam("authorityId", StaticValueExpression(""))
      .formParam("redirectionUrl", StaticValueExpression(traderUiBaseUrl))
      .formParam("credentialStrength", StaticValueExpression("strong"))
      .formParam("confidenceLevel", StaticValueExpression("50"))
      .formParam("affinityGroup", StaticValueExpression("Individual"))
      .formParam("enrolment[0].name", StaticValueExpression("HMRC-ATAR-ORG"))
      .formParam("enrolment[0].taxIdentifier[0].name", StaticValueExpression("EORINumber"))
      .formParam("enrolment[0].taxIdentifier[0].value", StaticValueExpression(eoriNumber))
      .formParam("enrolment[0].state", StaticValueExpression("Activated"))
      .check(status.is(HttpResponseStatus.SEE_OTHER.code()))
      .check(header(StaticValueExpression("Location")).is(traderUiBaseUrl))
}
