package requests

import io.gatling.core.Predef._
import io.gatling.core.check.CheckBuilder
import io.gatling.http.Predef.regex
import io.gatling.http.check.HttpCheck
import io.gatling.http.response.Response
import uk.gov.hmrc.performance.conf.ServicesConfiguration

trait BaseRequest extends ServicesConfiguration {

  lazy protected val baseUrl: String = baseUrlFor("binding-tariff-trader-frontend")

  protected val csrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)""""

  protected def saveCsrfToken: CheckBuilder[HttpCheck, Response, CharSequence, String] = {
    regex(_ => csrfPattern).saveAs("csrfToken")
  }

}
