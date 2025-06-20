# coding: utf-8

"""
    Unit API V3

    No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)

    OpenAPI spec version: 3.0
    
    Generated by: https://github.com/swagger-api/swagger-codegen.git
"""


from pprint import pformat
from six import iteritems
import re


class MeasurementEssenceImpl(object):
    """
    NOTE: This class is auto generated by the swagger code generator program.
    Do not edit the class manually.
    """


    """
    Attributes:
      swagger_types (dict): The key is attribute name
                            and the value is attribute type.
      attribute_map (dict): The key is attribute name
                            and the value is json key in definition.
    """
    swagger_types = {
        'ancestry': 'str',
        'type': 'str'
    }

    attribute_map = {
        'ancestry': 'ancestry',
        'type': 'type'
    }

    def __init__(self, ancestry=None, type=None):
        """
        MeasurementEssenceImpl - a model defined in Swagger
        """

        self._ancestry = None
        self._type = None

        if ancestry is not None:
          self.ancestry = ancestry
        if type is not None:
          self.type = type

    @property
    def ancestry(self):
        """
        Gets the ancestry of this MeasurementEssenceImpl.

        :return: The ancestry of this MeasurementEssenceImpl.
        :rtype: str
        """
        return self._ancestry

    @ancestry.setter
    def ancestry(self, ancestry):
        """
        Sets the ancestry of this MeasurementEssenceImpl.

        :param ancestry: The ancestry of this MeasurementEssenceImpl.
        :type: str
        """

        self._ancestry = ancestry

    @property
    def type(self):
        """
        Gets the type of this MeasurementEssenceImpl.

        :return: The type of this MeasurementEssenceImpl.
        :rtype: str
        """
        return self._type

    @type.setter
    def type(self, type):
        """
        Sets the type of this MeasurementEssenceImpl.

        :param type: The type of this MeasurementEssenceImpl.
        :type: str
        """

        self._type = type

    def to_dict(self):
        """
        Returns the model properties as a dict
        """
        result = {}

        for attr, _ in iteritems(self.swagger_types):
            value = getattr(self, attr)
            if isinstance(value, list):
                result[attr] = list(map(
                    lambda x: x.to_dict() if hasattr(x, "to_dict") else x,
                    value
                ))
            elif hasattr(value, "to_dict"):
                result[attr] = value.to_dict()
            elif isinstance(value, dict):
                result[attr] = dict(map(
                    lambda item: (item[0], item[1].to_dict())
                    if hasattr(item[1], "to_dict") else item,
                    value.items()
                ))
            else:
                result[attr] = value

        return result

    def to_str(self):
        """
        Returns the string representation of the model
        """
        return pformat(self.to_dict())

    def __repr__(self):
        """
        For `print` and `pprint`
        """
        return self.to_str()

    def __eq__(self, other):
        """
        Returns true if both objects are equal
        """
        if not isinstance(other, MeasurementEssenceImpl):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """
        Returns true if both objects are not equal
        """
        return not self == other
