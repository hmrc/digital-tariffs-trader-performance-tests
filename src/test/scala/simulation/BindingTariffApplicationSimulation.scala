package simulation

import requests.BindingTariffApplicationRequests._
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner

class BindingTariffApplicationSimulation extends PerformanceTestRunner {

  setup(id ="BTI application", description = "login and submit BTI application") withRequests (
    start,
    beforeYouStart
  )

  runSimulation()

}
