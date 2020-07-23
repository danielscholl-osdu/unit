
import os
from keycloak import KeycloakOpenID

def get_id_token():

    keycloak_host = os.getenv('KEYCLOAK_URL')
    keycloak_realm = os.getenv('KEYCLOAK_REALM', "OSDU")
    client_id = os.getenv('KEYCLOAK_CLIENT_ID')
    client_secret = os.getenv('KEYCLOAK_CLIENT_SECRET')

    user = os.getenv('AUTH_USER_ACCESS')
    password = os.getenv('AUTH_USER_ACCESS_PASSWORD')

    try:
        # Configure client
        keycloak_openid = KeycloakOpenID(server_url="https://"+keycloak_host+"/auth/",
                            client_id=client_id,
                            realm_name=keycloak_realm,
                            client_secret_key=client_secret)

        token = keycloak_openid.token(user, password)
        return token['access_token']
    except Exception as e:
        print(e)


def get_invalid_token():

    '''
    This is dummy jwt
    {
         "sub": "dummy@dummy.com",
         "iss": "dummy@dummy.com",
         "aud": "dummy.dummy.com",
         "iat": 1556137273,
         "exp": 1556223673,
         "provider": "dummy.com",
         "client": "dummy.com",
         "userid": "dummytester.com",
         "email": "dummytester.com",
         "authz": "",
         "lastname": "dummy",
         "firstname": "dummy",
         "country": "",
         "company": "",
         "jobtitle": "",
         "subid": "dummyid",
         "idp": "dummy",
         "hd": "dummy.com",
         "desid": "dummyid",
         "contact_email": "dummy@dummy.com"
    }
    '''

    return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkdW1teUBkdW1teS5jb20iLCJpc3MiOiJkdW1teUBkdW1teS5jb20iLCJhdWQiOiJkdW1teS5kdW1teS5jb20iLCJpYXQiOjE1NTYxMzcyNzMsImV4cCI6MTU1NjIzMDk3OSwicHJvdmlkZXIiOiJkdW1teS5jb20iLCJjbGllbnQiOiJkdW1teS5jb20iLCJ1c2VyaWQiOiJkdW1teXRlc3Rlci5jb20iLCJlbWFpbCI6ImR1bW15dGVzdGVyLmNvbSIsImF1dGh6IjoiIiwibGFzdG5hbWUiOiJkdW1teSIsImZpcnN0bmFtZSI6ImR1bW15IiwiY291bnRyeSI6IiIsImNvbXBhbnkiOiIiLCJqb2J0aXRsZSI6IiIsInN1YmlkIjoiZHVtbXlpZCIsImlkcCI6ImR1bW15IiwiaGQiOiJkdW1teS5jb20iLCJkZXNpZCI6ImR1bW15aWQiLCJjb250YWN0X2VtYWlsIjoiZHVtbXlAZHVtbXkuY29tIiwianRpIjoiNGEyMWYyYzItZjU5Yy00NWZhLTk0MTAtNDNkNDdhMTg4ODgwIn0.nkiyKtfXXxAlC60iDjXuB2EAGDfZiVglP-CyU1T4etc"
