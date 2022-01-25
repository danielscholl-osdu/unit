# swagger_client.InfoapiApi

All URIs are relative to *https://localhost:8080/api/unit*

Method | HTTP request | Description
------------- | ------------- | -------------
[**info_using_get**](InfoapiApi.md#info_using_get) | **GET** /info | info


# **info_using_get**
> VersionInfo info_using_get(data_partition_id)

info

### Example 
```python
from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# Configure API key authorization: Bearer Authorization
swagger_client.configuration.api_key['Authorization'] = 'YOUR_API_KEY'
# Uncomment below to setup prefix (e.g. Bearer) for API key, if needed
# swagger_client.configuration.api_key_prefix['Authorization'] = 'Bearer'

# create an instance of the API class
api_instance = swagger_client.InfoapiApi()
data_partition_id = 'opendes' # str | tenant (default to opendes)

try: 
    # info
    api_response = api_instance.info_using_get(data_partition_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling InfoapiApi->info_using_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data_partition_id** | **str**| tenant | [default to opendes]

### Return type

[**VersionInfo**](VersionInfo.md)

### Authorization

[Bearer Authorization](../README.md#Bearer Authorization)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

