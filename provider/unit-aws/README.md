# Unit Service
The Unit service provides unit management and conversion capabilities to services
within the OSDU on AWS energy data platform. The service allows lookup of dimension, measurement, 
and definitions for units available in a provided or customized catalog. Given two compatible units of
measure, the service can perform conversion.

## Running Locally

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
Pre-requisites

* JDK 17 (https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
* Maven 3.8.3 or later
* Lombok 1.18.26 or later
* OSDU Instance deployed on AWS

### Service Configuration
In order to run the service locally or remotely, you will need to have the following environment variables defined.

| name | example value | required | description | sensitive? |
| ---  | ---   | ---         | ---        | ---    |
| `APPLICATION_PORT` | `8080` | yes | The port the service will be hosted on. | no |
| `AWS_REGION` | `us-east-1` | yes | The region where resources needed by the service are deployed | no |
| `AWS_ACCESS_KEY_ID` | `ASIAXXXXXXXXXXXXXX` | yes | The AWS Access Key for a user with access to Backend Resources required by the service | yes |
| `AWS_SECRET_ACCESS_KEY` | `super-secret-key==` | yes | The AWS Secret Key for a user with access to Backend Resources required by the service | yes |
| `AWS_SESSION_TOKEN` | `session-token-xxxxxxxxxx` | no | AWS Session token needed if using an SSO user session to authenticate | yes |
| `ENVIRONMENT` | `osdu-prefix` | yes | The Resource Prefix defined during deployment | no |
| `LOG_LEVEL` | `DEBUG` | yes | The Log Level severity to use (https://www.tutorialspoint.com/log4j/log4j_logging_levels.htm) | no |
| `SSM_ENABLED` | `true` | yes | Set to 'true' to use SSM to resolve config properties, otherwise use env vars | no |
| `SSL_ENABLED` | `false` | no | Set to 'false' to disable SSL for local development | no |
| `ENTITLEMENTS_BASE_URL` | `http://localhost:8081` or `https://some-hosted-url` | yes | Specify the base url for an entitlements service instance. Can be run locally or remote | no |
| `UNIT_CATALOG_FILENAME` | `data/unit_catalog_v2.json` | no | Provides a reference to the unit catalog | no |

### Run Locally
Check that maven is installed:

example:
```bash
$ mvn --version
Apache Maven 3.8.3 (ff8e977a158738155dc465c6a97ffaf31982d739)
Maven home: /usr/local/Cellar/maven/3.8.3/libexec
Java version: 1.8.0_312, vendor: Amazon.com Inc., runtime: /Library/Java/JavaVirtualMachines/amazon-corretto-8.jdk/Contents/Home/jre
...
```

You may need to configure access to the remote maven repository that holds the OSDU dependencies. Copy one of the below files' content to your .m2 folder
* For development against the OSDU GitLab environment, leverage: `<REPO_ROOT>~/.mvn/community-maven.settings.xml`
* For development in an AWS Environment, leverage: `<REPO_ROOT>/provider/unit-aws/maven/settings.xml`

* Navigate to the service's root folder and run:

```bash
mvn clean package -pl unit-core,provider/unit-aws
```

* If you wish to build the project without running tests

```bash
mvn clean package -pl unit-core,provider/unit-aws -DskipTests
```

After configuring your environment as specified above, you can follow these steps to run the application. These steps should be invoked from the *repository root.*
<br/>
<br/>
NOTE: If not on osx/linux: Replace `*` with version numbers as defined in the provider/unit-aws/pom.xml file

```bash
java -jar provider/unit-aws/target/unit-aws-*.*.*-SNAPSHOT-spring-boot.jar
```

## Testing

### Running Integration Tests
This section describes how to run OSDU Integration tests (testing/unit-test-aws).

You will need to have the following environment variables defined.

| name | example value | description | sensitive?
 | ---  | ---   | ---         | ---        |
| `AWS_ACCESS_KEY_ID` | `ASIAXXXXXXXXXXXXXX` | The AWS Access Key for a user with access to Backend Resources required by the service | yes |
| `AWS_SECRET_ACCESS_KEY` | `super-secret-key==` | The AWS Secret Key for a user with access to Backend Resources required by the service | yes |
| `AWS_SESSION_TOKEN` | `session-token-xxxxxxxxx` | AWS Session token needed if using an SSO user session to authenticate | yes |
| `AWS_COGNITO_USER_POOL_ID` | `us-east-1_xxxxxxxx` | User Pool Id for the reference cognito | no |
| `AWS_COGNITO_CLIENT_ID` | `xxxxxxxxxxxx` | Client ID for the Auth Flow integrated with the Cognito User Pool | no |
| `AWS_COGNITO_AUTH_FLOW` | `USER_PASSWORD_AUTH` | Auth flow used by reference cognito deployment | no |
| `ADMIN_USER` | `int-test-user@testing.com` | Int Test Username | no |
| `USER_NO_ACCESS` | `no-access-user@testing.com` | Int Test No Access Username | no |
| `ADMIN_PASSWORD` | `some-secure-password` | Int Test User/NoAccessUser Password | yes |
| `UNIT_HOST` | `localhost:8080` | The url where the Unit API is hosted | no |


**Creating a new user to use for integration tests**
 ```
 aws cognito-idp admin-create-user --user-pool-id ${AWS_COGNITO_USER_POOL_ID} --username ${AWS_COGNITO_AUTH_PARAMS_USER} --user-attributes Name=email,Value=${AWS_COGNITO_AUTH_PARAMS_USER} Name=email_verified,Value=True --message-action SUPPRESS

 aws cognito-idp initiate-auth --auth-flow ${AWS_COGNITO_AUTH_FLOW} --client-id ${AWS_COGNITO_CLIENT_ID} --auth-parameters USERNAME=${AWS_COGNITO_AUTH_PARAMS_USER},PASSWORD=${AWS_COGNITO_AUTH_PARAMS_PASSWORD}
 ```

**Entitlements group configuration for integration accounts**
<br/>
In order to add user entitlements, run entitlements bootstrap scripts in the entitlements project

Execute following command to build code and run all the integration tests:

### Run Tests simulating Pipeline

* Prior to running tests, scripts must be executed locally to generate pipeline env vars

```bash
testing/unit_test_aws/build-aws/prepare-dist.sh

#Set Neccessary ENV Vars here as defined in run-tests.sh

dist/testing/integration/bin/unit_test_aws/build-aws/run-tests.sh 
```

## License
Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
