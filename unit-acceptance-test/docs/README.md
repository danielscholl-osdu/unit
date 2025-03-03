### Running E2E Tests

You will need to have the following environment variables defined.

| name                                | value                                              |                                                  | sensitive? | source        |
|-------------------------------------|----------------------------------------------------|--------------------------------------------------|------------|---------------|
| `VIRTUAL_SERVICE_HOST_NAME`         | eg. `osdu.core-dev.gcp.gnrg-osdu.projects.epam.com`| Host name of catalog service under test          | no         | -             |
| `MY_TENANT`                         | eg. `osdu`                                         | OSDU tenant used for testing                     | no         | -             |

Authentication can be provided as OIDC config:

| name                                            | value                                   | description                   | sensitive? | source |
|-------------------------------------------------|-----------------------------------------|-------------------------------|------------|--------|
| `PRIVILEGED_USER_OPENID_PROVIDER_CLIENT_ID`     | `********`                              | PRIVILEGED_USER Client Id     | yes        | -      |
| `PRIVILEGED_USER_OPENID_PROVIDER_CLIENT_SECRET` | `********`                              | PRIVILEGED_USER Client secret | yes        | -      |
| `TEST_OPENID_PROVIDER_URL`                      | `https://keycloak.com/auth/realms/osdu` | OpenID provider url           | yes        | -      |

Or tokens can be used directly from env variables:

| name                    | value      | description           | sensitive? | source |
|-------------------------|------------|-----------------------|------------|--------|
| `PRIVILEGED_USER_TOKEN` | `********` | PRIVILEGED_USER Token | yes        | -      |


Execute following command to build code and run all the integration tests:

 ```bash
 # Note: this assumes that the environment variables for acceptance tests as outlined
 #       above are already exported in your environment.
 # run acceptance tests
 $ cd unit-acceptance-test
 $ chmod +x ./run-acceptance-tests.sh
 $ ./run-acceptance-tests.sh
 ```

