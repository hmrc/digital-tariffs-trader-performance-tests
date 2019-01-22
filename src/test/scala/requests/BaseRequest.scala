package requests

import io.gatling.core.Predef._
import io.gatling.http.Predef.regex
import uk.gov.hmrc.performance.conf.ServicesConfiguration

trait BaseRequest extends ServicesConfiguration {

  val csrfPattern = """<input type="hidden" name="csrfToken" value="([^"]+)""""
  def saveCsrfToken = regex(_ => csrfPattern).saveAs("csrfToken")

}
