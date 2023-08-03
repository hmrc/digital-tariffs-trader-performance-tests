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
Run an instance of Mongo database. Version 4.2 is recommended.

#### Localstack
Please ensure you have Docker installed before continuing with these steps.

You might need to modify your `~/.bash_profile` file by adding `export PATH="/Users/<USR>/Library/Python/2.7/bin:$PATH"`
(Run `source ~/.bash_profile` for reloading it without restarting your machine) (one time only)

Install localstack:

`pip install localstack --user --upgrade --ignore-installed six` (one time only)

`pip install amazon_kclpy` (one time only)

`localstack start` (one time only)

Start localstack

`SERVICES=s3 localstack start` (we need only the S3 service)

Set up aws cli credentials by running `aws configure` (one time only)
```
AWS Access Key ID [None]: test
AWS Secret Access Key [None]: dGVzdA==
Default region name [None]: eu-west-2
Default output format [None]:
```

Create the bucket

`aws --endpoint-url=http://localhost:4572 s3 mb s3://digital-tariffs-local`

Then check this URL is available in the browser http://localhost:4572/digital-tariffs-local

#### Run required microservices
Run the following command:
```
sm2 --start DIGITAL_TARIFFS
sm2 --stop PDF_GENERATOR_SERVICE
sm2 --start PDF_GENERATOR_SERVICE:1.20.0
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
