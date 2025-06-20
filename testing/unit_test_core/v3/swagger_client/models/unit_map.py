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


class UnitMap(object):
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
        'from_namespace': 'str',
        'to_namespace': 'str',
        'unit_map_item_count': 'int',
        'unit_map_items': 'list[UnitMapItem]'
    }

    attribute_map = {
        'from_namespace': 'fromNamespace',
        'to_namespace': 'toNamespace',
        'unit_map_item_count': 'unitMapItemCount',
        'unit_map_items': 'unitMapItems'
    }

    def __init__(self, from_namespace=None, to_namespace=None, unit_map_item_count=None, unit_map_items=None):
        """
        UnitMap - a model defined in Swagger
        """

        self._from_namespace = None
        self._to_namespace = None
        self._unit_map_item_count = None
        self._unit_map_items = None

        if from_namespace is not None:
          self.from_namespace = from_namespace
        if to_namespace is not None:
          self.to_namespace = to_namespace
        if unit_map_item_count is not None:
          self.unit_map_item_count = unit_map_item_count
        if unit_map_items is not None:
          self.unit_map_items = unit_map_items

    @property
    def from_namespace(self):
        """
        Gets the from_namespace of this UnitMap.

        :return: The from_namespace of this UnitMap.
        :rtype: str
        """
        return self._from_namespace

    @from_namespace.setter
    def from_namespace(self, from_namespace):
        """
        Sets the from_namespace of this UnitMap.

        :param from_namespace: The from_namespace of this UnitMap.
        :type: str
        """

        self._from_namespace = from_namespace

    @property
    def to_namespace(self):
        """
        Gets the to_namespace of this UnitMap.

        :return: The to_namespace of this UnitMap.
        :rtype: str
        """
        return self._to_namespace

    @to_namespace.setter
    def to_namespace(self, to_namespace):
        """
        Sets the to_namespace of this UnitMap.

        :param to_namespace: The to_namespace of this UnitMap.
        :type: str
        """

        self._to_namespace = to_namespace

    @property
    def unit_map_item_count(self):
        """
        Gets the unit_map_item_count of this UnitMap.

        :return: The unit_map_item_count of this UnitMap.
        :rtype: int
        """
        return self._unit_map_item_count

    @unit_map_item_count.setter
    def unit_map_item_count(self, unit_map_item_count):
        """
        Sets the unit_map_item_count of this UnitMap.

        :param unit_map_item_count: The unit_map_item_count of this UnitMap.
        :type: int
        """

        self._unit_map_item_count = unit_map_item_count

    @property
    def unit_map_items(self):
        """
        Gets the unit_map_items of this UnitMap.

        :return: The unit_map_items of this UnitMap.
        :rtype: list[UnitMapItem]
        """
        return self._unit_map_items

    @unit_map_items.setter
    def unit_map_items(self, unit_map_items):
        """
        Sets the unit_map_items of this UnitMap.

        :param unit_map_items: The unit_map_items of this UnitMap.
        :type: list[UnitMapItem]
        """

        self._unit_map_items = unit_map_items

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
        if not isinstance(other, UnitMap):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """
        Returns true if both objects are not equal
        """
        return not self == other
