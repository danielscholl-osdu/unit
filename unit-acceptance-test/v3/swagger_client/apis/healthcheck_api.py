# coding: utf-8

"""
    Unit API V3

    No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)

    OpenAPI spec version: 3.0
    
    Generated by: https://github.com/swagger-api/swagger-codegen.git
"""


from __future__ import absolute_import

import sys
import os
import re

# python 2 and python 3 compatibility library
from six import iteritems

from ..configuration import Configuration
from ..api_client import ApiClient


class HealthcheckApi(object):
    """
    NOTE: This class is auto generated by the swagger code generator program.
    Do not edit the class manually.
    Ref: https://github.com/swagger-api/swagger-codegen
    """

    def __init__(self, api_client=None):
        config = Configuration()
        if api_client:
            self.api_client = api_client
        else:
            if not config.api_client:
                config.api_client = ApiClient()
            self.api_client = config.api_client

    def liveness_check_using_get(self, data_partition_id, **kwargs):
        """
        livenessCheck
        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please define a `callback` function
        to be invoked when receiving the response.
        >>> def callback_function(response):
        >>>     pprint(response)
        >>>
        >>> thread = api.liveness_check_using_get(data_partition_id, callback=callback_function)

        :param callback function: The callback function
            for asynchronous request. (optional)
        :param str data_partition_id: tenant (required)
        :return: ResponseEntity
                 If the method is called asynchronously,
                 returns the request thread.
        """
        kwargs['_return_http_data_only'] = True
        if kwargs.get('callback'):
            return self.liveness_check_using_get_with_http_info(data_partition_id, **kwargs)
        else:
            (data) = self.liveness_check_using_get_with_http_info(data_partition_id, **kwargs)
            return data

    def liveness_check_using_get_with_http_info(self, data_partition_id, **kwargs):
        """
        livenessCheck
        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please define a `callback` function
        to be invoked when receiving the response.
        >>> def callback_function(response):
        >>>     pprint(response)
        >>>
        >>> thread = api.liveness_check_using_get_with_http_info(data_partition_id, callback=callback_function)

        :param callback function: The callback function
            for asynchronous request. (optional)
        :param str data_partition_id: tenant (required)
        :return: ResponseEntity
                 If the method is called asynchronously,
                 returns the request thread.
        """

        all_params = ['data_partition_id']
        all_params.append('callback')
        all_params.append('_return_http_data_only')
        all_params.append('_preload_content')
        all_params.append('_request_timeout')

        params = locals()
        for key, val in iteritems(params['kwargs']):
            if key not in all_params:
                raise TypeError(
                    "Got an unexpected keyword argument '%s'"
                    " to method liveness_check_using_get" % key
                )
            params[key] = val
        del params['kwargs']
        # verify the required parameter 'data_partition_id' is set
        if ('data_partition_id' not in params) or (params['data_partition_id'] is None):
            raise ValueError("Missing the required parameter `data_partition_id` when calling `liveness_check_using_get`")


        collection_formats = {}

        path_params = {}

        query_params = []

        header_params = {}
        if 'data_partition_id' in params:
            header_params['data-partition-id'] = params['data_partition_id']

        form_params = []
        local_var_files = {}

        body_params = None
        # HTTP header `Accept`
        header_params['Accept'] = self.api_client.\
            select_header_accept(['*/*'])

        # HTTP header `Content-Type`
        header_params['Content-Type'] = self.api_client.\
            select_header_content_type(['application/json'])

        # Authentication setting
        auth_settings = ['Bearer Authorization']

        return self.api_client.call_api('/_ah/liveness_check', 'GET',
                                        path_params,
                                        query_params,
                                        header_params,
                                        body=body_params,
                                        post_params=form_params,
                                        files=local_var_files,
                                        response_type='ResponseEntity',
                                        auth_settings=auth_settings,
                                        callback=params.get('callback'),
                                        _return_http_data_only=params.get('_return_http_data_only'),
                                        _preload_content=params.get('_preload_content', True),
                                        _request_timeout=params.get('_request_timeout'),
                                        collection_formats=collection_formats)

    def readiness_check_using_get(self, data_partition_id, **kwargs):
        """
        readinessCheck
        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please define a `callback` function
        to be invoked when receiving the response.
        >>> def callback_function(response):
        >>>     pprint(response)
        >>>
        >>> thread = api.readiness_check_using_get(data_partition_id, callback=callback_function)

        :param callback function: The callback function
            for asynchronous request. (optional)
        :param str data_partition_id: tenant (required)
        :return: ResponseEntity
                 If the method is called asynchronously,
                 returns the request thread.
        """
        kwargs['_return_http_data_only'] = True
        if kwargs.get('callback'):
            return self.readiness_check_using_get_with_http_info(data_partition_id, **kwargs)
        else:
            (data) = self.readiness_check_using_get_with_http_info(data_partition_id, **kwargs)
            return data

    def readiness_check_using_get_with_http_info(self, data_partition_id, **kwargs):
        """
        readinessCheck
        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please define a `callback` function
        to be invoked when receiving the response.
        >>> def callback_function(response):
        >>>     pprint(response)
        >>>
        >>> thread = api.readiness_check_using_get_with_http_info(data_partition_id, callback=callback_function)

        :param callback function: The callback function
            for asynchronous request. (optional)
        :param str data_partition_id: tenant (required)
        :return: ResponseEntity
                 If the method is called asynchronously,
                 returns the request thread.
        """

        all_params = ['data_partition_id']
        all_params.append('callback')
        all_params.append('_return_http_data_only')
        all_params.append('_preload_content')
        all_params.append('_request_timeout')

        params = locals()
        for key, val in iteritems(params['kwargs']):
            if key not in all_params:
                raise TypeError(
                    "Got an unexpected keyword argument '%s'"
                    " to method readiness_check_using_get" % key
                )
            params[key] = val
        del params['kwargs']
        # verify the required parameter 'data_partition_id' is set
        if ('data_partition_id' not in params) or (params['data_partition_id'] is None):
            raise ValueError("Missing the required parameter `data_partition_id` when calling `readiness_check_using_get`")


        collection_formats = {}

        path_params = {}

        query_params = []

        header_params = {}
        if 'data_partition_id' in params:
            header_params['data-partition-id'] = params['data_partition_id']

        form_params = []
        local_var_files = {}

        body_params = None
        # HTTP header `Accept`
        header_params['Accept'] = self.api_client.\
            select_header_accept(['*/*'])

        # HTTP header `Content-Type`
        header_params['Content-Type'] = self.api_client.\
            select_header_content_type(['application/json'])

        # Authentication setting
        auth_settings = ['Bearer Authorization']

        return self.api_client.call_api('/_ah/readiness_check', 'GET',
                                        path_params,
                                        query_params,
                                        header_params,
                                        body=body_params,
                                        post_params=form_params,
                                        files=local_var_files,
                                        response_type='ResponseEntity',
                                        auth_settings=auth_settings,
                                        callback=params.get('callback'),
                                        _return_http_data_only=params.get('_return_http_data_only'),
                                        _preload_content=params.get('_preload_content', True),
                                        _request_timeout=params.get('_request_timeout'),
                                        collection_formats=collection_formats)
