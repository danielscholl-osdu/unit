# unit-service

unit-service is a [Spring Boot](https://spring.io/projects/spring-boot) service that hosts Units of Measure catalog and conversion services.

## Running Locally

### Requirements

In order to run this service locally, you will need the following:

- [Maven 3.6.0+](https://maven.apache.org/download.cgi)
- [AdoptOpenJDK8](https://adoptopenjdk.net/)
- Infrastructure dependencies, deployable through the relevant [infrastructure template](https://dev.azure.com/slb-des-ext-collaboration/open-data-ecosystem/_git/infrastructure-templates?path=%2Finfra&version=GBmaster&_a=contents)
- While not a strict dependency, example commands in this document use [bash](https://www.gnu.org/software/bash/)

### General Tips

**Environment Variable Management**
The following tools make environment variable configuration simpler
 - [direnv](https://direnv.net/) - for a shell/terminal environment
 - [EnvFile](https://plugins.jetbrains.com/plugin/7861-envfile) - for [Intellij IDEA](https://www.jetbrains.com/idea/)

**Lombok**
This project uses [Lombok](https://projectlombok.org/) for code generation. You may need to configure your IDE to take advantage of this tool.
 - [Intellij configuration](https://projectlombok.org/setup/intellij)
 - [VSCode configuration](https://projectlombok.org/setup/vscode)


### Environment Variables

In order to run the service locally, you will need to have the following environment variables defined.

**Note** The following command can be useful to pull secrets from keyvault:
```bash
az keyvault secret show --vault-name $KEY_VAULT_NAME --name $KEY_VAULT_SECRET_NAME --query value -otsv
```

**Required to run service**

| name | value | description | sensitive? | source |
| ---  | ---   | ---         | ---        | ---    |
| `KEYVAULT_URI` | ex `https://foo-keyvault.vault.azure.net/` | URI of KeyVault that holds application secrets | no | output of infrastructure deployment |
| `ENTITLEMENT_URL` | ex `https://foo-entitlements.azurewebsites.net` | Entitlements API endpoint | no | output of infrastructure deployment |
| `appinsights_key` | `********` | API Key for App Insights | yes | output of infrastructure deployment |
| `osdu_unit_catalog_filename` | `data/unit_catalog_v2.json` | Flag to Disable AAD auth | no | -- |

**Required to run integration tests**

| name | value | description | sensitive? | source |
| ---  | ---   | ---         | ---        | ---    |
| `VIRTUAL_SERVICE_HOST_NAME` | `localhost:8080` | Hostname of Service | no | -- |
| `INTEGRATION_TESTER` | `********` | System identity to assume for API calls. Note: this user must have entitlements configured already | no | -- |
| `AZURE_TESTER_SERVICEPRINCIPAL_SECRET` | `********` | Secret for `$INTEGRATION_TESTER` | yes | -- |
| `AZURE_AD_TENANT_ID` | `********` | AD tenant to authenticate users from | yes | -- |
| `AZURE_AD_APP_RESOURCE_ID` | `********` | AAD client application ID | yes | output of infrastructure deployment |
| `BASE_URL` | `/api/unit` | URI Context Path | no | -- |
| `MY_TENANT` | ex `opendes` | OSDU tenant used for testing | no | -- |


### Configure Maven

Check that maven is installed:
```bash
$ mvn --version
Apache Maven 3.6.0
Maven home: /usr/share/maven
Java version: 1.8.0_212, vendor: AdoptOpenJDK, runtime: /usr/lib/jvm/jdk8u212-b04/jre
...
```

### Build and run the application

After configuring your environment as specified above, you can follow these steps to build and run the application. These steps should be invoked from the *repository root.*

```bash
# build + test + install core service code
$ mvn clean install

# build + test + package azure service code
$ (cd provider/unit-azure/unit-aks/ && mvn clean package)

# run service
#
# Note: this assumes that the environment variables for running the service as outlined
#       above are already exported in your environment.
$ java -jar $(find provider/unit-azure/unit-aks/target/ -name '*-spring-boot.jar')
```

### Test the application

After the service has started it should be accessible via a web browser by visiting [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html). If the request does not fail, you can then run the integration tests.

These tests are written in python follow the process below for a linux/mac.

## Building/running
Go to the provider folder:
```bash
cd testing/unit-test-azure

# Execute the bash test script
./run-integration-tests.sh
```

## Deploying service to Azure

Service deployments into Azure are standardized to make the process the same for all services if using ADO and are closely related to the infrastructure deployed. The steps to deploy into Azure can be [found here](https://github.com/azure/osdu-infrastructure)

The default ADO pipeline is /devops/azure/pipeline.yml


## License
Copyright © Microsoft Corporation

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
