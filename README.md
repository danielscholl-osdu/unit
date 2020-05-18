# Units of Measure Service

## This repository contains 
1. The Java implementation of the Units of Measure catalog and conversion service (aka dps-unit-service). The Java code is located in the ```src``` folder. To open the Java project, open ```pom.xml```.
1. Tests are located in ```src/test/java/org/opengroup/osdu/unitservice/...```
1. The openapi specification file is `unit_service_openapi_v2.json`.
1. Python integration and health tests in the ```testing``` folder. 
See also the test's [README.md](testing/README.md)

## Prerequisites
1. The project builds with [maven](https://maven.apache.org/). Make sure maven is installed locally.
1. The project requires the [Lombok](https://projectlombok.org/) plug-in installed for your IDE.

### Build service and run unit tests

```sh
mvn clean install
```

## Running Azure Unit Service locally
### Configure Maven Settings
To obtain maven dependencies from the **Azure DevOps Artifacts** we need to configure the value for variable ${VSTS_FEED_TOKEN} described in `maven\settings.xml`:
- **Get token:**  
We can use personal token generated in VSTS on [Personal Access Tokens](https://dev.azure.com/slb-swt/_usersSettings/tokens) > New token > Organization: slb-des-ext-collaboration > Create
- **Set token in your local home folder:**  
Open or create `USER_HOME_FOLDER\.m2\settings.xml` and paste your personal token in `<password></password>`section.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <servers>
        <server>
          <id>os-core</id>
          <username>slb-des-ext-collaboration</username>
          <password></password>
        </server>
  </servers>
</settings>
```
#### Build and run Unit Service locally using bash
- Set the required environments described in [Build](##Build) and [Release/deployment](##Release/deployment) sections
- Navigate to the Unit Service's root folder ```os-unit-service``` 
- Build core and run unit tests in command line:
```bash
mvn clean install -P unit-core
# To run without tests add -Dmaven.test.skip=true
```
- Navigate to the Unit Service's Azure 
- Build services in command line:
```bash
mvn clean package -P unit-aks,unit-core
```
- Navigate to the Unit Service's root folder ```os-unit-service``` 
- Run application with command
```bash
java -jar provider/unit-azure/unit-aks/target/unit-aks-1.0.0.jar
```

#### Running Azure Unit Service using IntelliJ IDEA
Navigate to the **Create Run/Debug Configuration** tool
Select **'Add New Configuration'** and select **Application**

Type the next commands into the suggested fields: 
- Working directory: ```{path_to_the_unit}/os-unit-service``` 
- Main class: ```org.opengroup.osdu.unitservice.UomAksApplication``` 
- Use classpath of module:  ```unit-aks```  
***Note: If you don't see "unit-aks" in the dropdown menu - find appropriate pom.xml and click "Add as a Maven project"***
- Environment variables: Set the required environments described in [Build](##Build) and [Release/deployment](##Release/deployment) section

Execute **Run** or **Debug** for configured Application


### Debug locally - e.g. using Postman
In the Postman Settings / General, turn SSL certificate validation off when running locally.
Similarly, when not using Postman but client code, set the configuration  ```verify_ssl``` false (see [instructions](https://github.com/swagger-api/swagger-codegen/issues/7778))

Run application using debug mode and use [Postman](https://www.getpostman.com/)
to send a GET request to obtaining the Swagger API documentation:
```
http://localhost:8080/api/unit/v2/swagger.json
```
or send a POST request to the service:
```
http://localhost:8080/api/unit/v2/...
```
Open the Swagger-UI:
```
http://localhost:8080/api/unit/swagger-ui.html
```

Headers for Postman:
| Key | Value |
|----------|----------|
| Authorization | Bearer `<token>` |
| data-partition-id | $MY_TENANT (see [testing\README.md](testing/README.md)) |

### Build and run the Docker container locally
1. Run the `maven run` command to have the .jar file generated.
1. Have the Azure subscription set up 
1. Open a Powershell
1. Install the Azure CLI locally
1. Authenticate yourself to Azure Container Registry (acr) with the following command:
```az acr login --name delfi```
1. Execute the following command to build the container image:
```docker build -t unit .```
1. Execute the following command to build the container image:
```docker run -t --rm -p 8080:8080 unit```
1. Use Postman or curl to try out the endpoints


## Build
VSTS build definition is located at build definitions/dps/unit-service, which 
requires the following environment variables:

| Variable | Contents |
|----------|----------|
| UNIT_CATALOG_BUCKET | Optional, bucket name where unit catalogs are located. |
| UNIT_CATALOG_FILENAME | Required, file name for the unit catalog to use. Default to UnitCatalog_V2.json |

## Release/deployment
VSTS release definition is located at provider\unit-azure\unit-aks\devops, which 
requires the following environment variables:

| Variable | Contents |
|----------|----------|
| ENTITLEMENT_URL | Required |
