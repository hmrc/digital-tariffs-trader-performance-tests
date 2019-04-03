
# Digital Tariffs Performance Tests

This repository contains the Gatling performance/load tests for the Digital Tariffs microservices.

---

## Commands for running the Gatling tests from the local terminal

#### command for running all Gatling simulations 
```
sbt gatling:test
```

#### commands for running the Gatling simulations individually:
```
sbt 'testOnly uk.gov.hmrc.perftests.digitaltariffs.traderui.TraderUiSimulation'
sbt 'testOnly uk.gov.hmrc.perftests.digitaltariffs.adviceui.AdviceUiSimulation'
sbt 'testOnly uk.gov.hmrc.perftests.digitaltariffs.rulingui.RulingUiSimulation'
sbt 'testOnly uk.gov.hmrc.perftests.digitaltariffs.operatorui.OperatorUiSimulation'
```

---

## Jenkins

#### Jenkins job
https://deploy-staging.tax.service.gov.uk/job/digital-tariffs-performance-tests/

#### Jenkins configurations
https://github.com/hmrc/jenkins-config/blob/master/jobs/orchestration/staging/aws_eu_west_1/digital-tariffs.groovy

---

#### Performance testing documentation 
https://confluence.tools.tax.service.gov.uk/display/DTRG/Performance+Testing
