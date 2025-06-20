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


class ABCD(object):
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
        'a': 'float',
        'b': 'float',
        'c': 'float',
        'd': 'float'
    }

    attribute_map = {
        'a': 'a',
        'b': 'b',
        'c': 'c',
        'd': 'd'
    }

    def __init__(self, a=None, b=None, c=None, d=None):
        """
        ABCD - a model defined in Swagger
        """

        self._a = None
        self._b = None
        self._c = None
        self._d = None

        if a is not None:
          self.a = a
        if b is not None:
          self.b = b
        if c is not None:
          self.c = c
        if d is not None:
          self.d = d

    @property
    def a(self):
        """
        Gets the a of this ABCD.

        :return: The a of this ABCD.
        :rtype: float
        """
        return self._a

    @a.setter
    def a(self, a):
        """
        Sets the a of this ABCD.

        :param a: The a of this ABCD.
        :type: float
        """

        self._a = a

    @property
    def b(self):
        """
        Gets the b of this ABCD.

        :return: The b of this ABCD.
        :rtype: float
        """
        return self._b

    @b.setter
    def b(self, b):
        """
        Sets the b of this ABCD.

        :param b: The b of this ABCD.
        :type: float
        """

        self._b = b

    @property
    def c(self):
        """
        Gets the c of this ABCD.

        :return: The c of this ABCD.
        :rtype: float
        """
        return self._c

    @c.setter
    def c(self, c):
        """
        Sets the c of this ABCD.

        :param c: The c of this ABCD.
        :type: float
        """

        self._c = c

    @property
    def d(self):
        """
        Gets the d of this ABCD.

        :return: The d of this ABCD.
        :rtype: float
        """
        return self._d

    @d.setter
    def d(self, d):
        """
        Sets the d of this ABCD.

        :param d: The d of this ABCD.
        :type: float
        """

        self._d = d

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
        if not isinstance(other, ABCD):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """
        Returns true if both objects are not equal
        """
        return not self == other
