# digital-tariffs-trader-performance-tests

## Commands for running the Gatling tests from the local terminal

#### command for running all Gatling simulations 
##### command for running all Gatling simulations in Staging
```shell
./run.sh
```
OR
```shell
./run_smoke_test.sh 
```

## Jenkins

#### Jenkins job
https://performance.tools.staging.tax.service.gov.uk/job/digital-tariffs-trader-performance-tests/
---

#### Jenkins configurations
https://github.com/hmrc/performance-test-jobs/blob/master/jobs/live/digital_tariffs.groovy
---

#### Performance testing documentation 
https://confluence.tools.tax.service.gov.uk/display/DTRG/Performance+Testing
---

# digital-tariffs-trader-performance-tests

### License
This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").

## Run the tests locally

#### Service Manager 2
Install Service Manager 2:
https://github.com/hmrc/sm2

Clone this project:
https://github.com/hmrc/service-manager-config/

#### Mongo
Run an instance of Mongo database. Version 5.0 is recommended.

#### Moto (S3)
Please ensure you have Docker installed before continuing with these steps.

Start [Moto](https://github.com/getmoto/moto) (MotoServer) on port 4566:

`docker run -d --restart unless-stopped --name moto -p 4566:5000 motoserver/moto:5.1.18`

Set up aws cli credentials by running `aws configure` (one time only)
```
AWS Access Key ID [None]: test
AWS Secret Access Key [None]: dGVzdA==
Default region name [None]: eu-west-2
Default output format [None]:
```

Create the bucket

`aws --endpoint-url=http://localhost:4566 s3 mb s3://digital-tariffs-local`

Then check this URL is available in the browser http://localhost:4566/digital-tariffs-local

#### Run required microservices
Run the following command:
```
sm2 --start DIGITAL_TARIFFS
sm2 --start FEEDBACK_FRONTEND

Stop any relevant services that you will be running locally through the console:
eg. sm2 --stop BINDING_TARIFF_CLASSIFICATION
```

##### command for running all Gatling simulations in Localhost
```shell
./run_local.sh
```
OR
```shell
./run_smoke_test_local.sh
```
