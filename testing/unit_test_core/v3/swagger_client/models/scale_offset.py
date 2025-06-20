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


class ScaleOffset(object):
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
        'offset': 'float',
        'scale': 'float'
    }

    attribute_map = {
        'offset': 'offset',
        'scale': 'scale'
    }

    def __init__(self, offset=None, scale=None):
        """
        ScaleOffset - a model defined in Swagger
        """

        self._offset = None
        self._scale = None

        if offset is not None:
          self.offset = offset
        if scale is not None:
          self.scale = scale

    @property
    def offset(self):
        """
        Gets the offset of this ScaleOffset.

        :return: The offset of this ScaleOffset.
        :rtype: float
        """
        return self._offset

    @offset.setter
    def offset(self, offset):
        """
        Sets the offset of this ScaleOffset.

        :param offset: The offset of this ScaleOffset.
        :type: float
        """

        self._offset = offset

    @property
    def scale(self):
        """
        Gets the scale of this ScaleOffset.

        :return: The scale of this ScaleOffset.
        :rtype: float
        """
        return self._scale

    @scale.setter
    def scale(self, scale):
        """
        Sets the scale of this ScaleOffset.

        :param scale: The scale of this ScaleOffset.
        :type: float
        """

        self._scale = scale

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
        if not isinstance(other, ScaleOffset):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """
        Returns true if both objects are not equal
        """
        return not self == other
