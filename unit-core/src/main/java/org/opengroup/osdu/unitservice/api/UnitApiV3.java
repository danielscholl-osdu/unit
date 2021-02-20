package org.opengroup.osdu.unitservice.api;


import static org.opengroup.osdu.unitservice.helper.Utility.createQueryResultForUnits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.opengroup.osdu.unitservice.helper.Utility;
import org.opengroup.osdu.unitservice.interfaces.ABCD;
import org.opengroup.osdu.unitservice.interfaces.Catalog;
import org.opengroup.osdu.unitservice.interfaces.CatalogLastModified;
import org.opengroup.osdu.unitservice.interfaces.ConversionResult;
import org.opengroup.osdu.unitservice.interfaces.Measurement;
import org.opengroup.osdu.unitservice.interfaces.MeasurementMapItem;
import org.opengroup.osdu.unitservice.interfaces.QueryResult;
import org.opengroup.osdu.unitservice.interfaces.ScaleOffset;
import org.opengroup.osdu.unitservice.interfaces.Unit;
import org.opengroup.osdu.unitservice.interfaces.UnitMapItem;
import org.opengroup.osdu.unitservice.interfaces.UnitSystem;
import org.opengroup.osdu.unitservice.logging.AuditLogger;
import org.opengroup.osdu.unitservice.model.CatalogImpl;
import org.opengroup.osdu.unitservice.model.CatalogLastModifiedImpl;
import org.opengroup.osdu.unitservice.model.MeasurementEssenceImpl;
import org.opengroup.osdu.unitservice.model.MeasurementMapImpl;
import org.opengroup.osdu.unitservice.model.UnitEssenceImpl;
import org.opengroup.osdu.unitservice.model.UnitMapImpl;
import org.opengroup.osdu.unitservice.model.extended.CatalogResponse;
import org.opengroup.osdu.unitservice.model.extended.QueryResultImpl;
import org.opengroup.osdu.unitservice.model.extended.UnitSystemEssenceImpl;
import org.opengroup.osdu.unitservice.model.extended.UnitSystemInfoResponse;
import org.opengroup.osdu.unitservice.request.ConversionABCDRequest;
import org.opengroup.osdu.unitservice.request.ConversionScaleOffsetRequest;
import org.opengroup.osdu.unitservice.request.MeasurementRequest;
import org.opengroup.osdu.unitservice.request.SearchRequest;
import org.opengroup.osdu.unitservice.request.UnitRequest;
import org.opengroup.osdu.unitservice.request.UnitSystemRequest;
import org.opengroup.osdu.unitservice.util.AppException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v3")
public class UnitApiV3 {

  private CatalogImpl catalog;
  private AuditLogger auditLogger;

  public UnitApiV3(CatalogImpl catalog, AuditLogger auditLogger) {
    this.catalog = catalog;
    this.auditLogger = auditLogger;
  }

  void assertRange(int offset, int limit) {
    StringBuilder stringBuilder = new StringBuilder();
    if (offset < 0) {
      stringBuilder.append("Offset can not be negative. ");
    }

    if (limit < -1) {
      stringBuilder.append(
          "'-1' is the only valid negative value and means all. Other negative values for limit is invalid.");
    }

    if (stringBuilder.length() > 0) {
      throw AppException.createBadRequest(stringBuilder.toString());
    }
  }

  /********************************************
   Catalog related API
   *********************************************/
  /**
   * Gets the full catalog
   *
   * @return A full catalog
   */
  @GetMapping("/catalog")
  public Catalog getCatalog() {
    CatalogResponse catalogResponse = catalog.getCatalogResponse();
    auditLogger.readCatalogSuccess(Collections.singletonList(catalogResponse.toString()));
    return catalog.getCatalogResponse();
  }

  /**
   * Gets a string representation of the date and time when the catalog was last changed.
   *
   * @return the last update time.
   */
  @GetMapping("/catalog/lastmodified")
  public CatalogLastModified getLastModified() {
    CatalogLastModified catalogLastModified = new CatalogLastModifiedImpl(
        catalog.getLastModified());
    auditLogger
        .readCatalogLastModifiedSuccess(Collections.singletonList(catalogLastModified.toString()));
    return catalogLastModified;
  }

  /********************************************
   Measurement related API
   *********************************************/
  /**
   * Gets a {@link QueryResult} with a collection of {@link Measurement} with given range
   *
   * @param offset The offset of the first item in the list of all measurements. It is optional and
   *               is 0 by default.
   * @param limit  The maximum number of the measurements returned. Minus 1("-1") means all. It is
   *               optional and is 100 by default.
   * @return a {@link QueryResult} with a collection of {@link Measurement}
   * @throws AppException An exception will be thrown if the startIndex is out of the range
   */
  @GetMapping("/measurement/list")
  public QueryResult getMeasurements(@RequestParam(value = "offset", defaultValue = "0") int offset,
      @RequestParam(value = "limit", defaultValue = "100") int limit) {
    assertRange(offset, limit);
    try {
      QueryResult queryResultForMeasurements = Utility
          .createQueryResultForMeasurements(catalog.getMeasurements(offset, limit));
      auditLogger.readMeasurementsSuccess(
          Collections.singletonList(queryResultForMeasurements.toString()));
      return queryResultForMeasurements;
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * Gets the measurement from the given essence Json {@link Measurement}. When no measurement in
   * the catalog has essence same as essence, a new measurement with deprecation state tagged as
   * "unresolved" will be returned; otherwise, an exception will be thrown.
   *
   * @param request MeasurementRequest
   * @return a base or child measurement
   * @throws AppException An exception will be thrown if the essence or code is invalid
   */
  @PostMapping("/measurement")
  public Measurement postMeasurement(@RequestBody MeasurementRequest request) {
    try {
      MeasurementEssenceImpl essence = request.getMeasurementEssence();
      Measurement measurement = catalog.postMeasurement(essence);
      auditLogger.readSpecificMeasurementSuccess(Collections.singletonList(measurement.toString()));
      return measurement;
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * Gets the measurement from the given ancestry of {@link Measurement}. When no measurement in the
   * catalog has ancestry same as ancestry, a new measurement with deprecation state tagged as
   * "unresolved" will be returned; otherwise, an exception will be thrown.
   *
   * @param ancestry ancestry of the measurement
   * @return a base or child measurement
   * @throws AppException An exception will be thrown if the ancestry is invalid
   */
  @GetMapping("/measurement")
  public Measurement getMeasurement(@RequestParam("ancestry") String ancestry) {

    try {
      Measurement measurement = catalog.getMeasurement(ancestry);
      auditLogger.readSpecificMeasurementSuccess(Collections.singletonList(measurement.toString()));
      return measurement;
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /********************************************
   Unit related API
   *********************************************/
  /**
   * Gets a list of the unitEssences by range
   *
   * @param offset The offset of the first item in the list of all unitEssences. It is optional and
   *               is 0 by default.
   * @param limit  The maximum number of the unitEssences returned. Minus 1("-1") means all. It is
   *               optional and is 100 by default.
   * @return IQueryResult
   * @throws AppException An exception will be thrown if the startIndex is out of the range
   */
  @GetMapping("/unit")
  public QueryResult getUnits(@RequestParam(value = "offset", defaultValue = "0") int offset,
      @RequestParam(value = "limit", defaultValue = "100") int limit) {
    assertRange(offset, limit);
    try {
      QueryResult queryResultForUnits = createQueryResultForUnits(catalog.getUnits(offset, limit));
      auditLogger.readUnitsSuccess(Collections.singletonList(queryResultForUnits.toString()));
      return queryResultForUnits;
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * Gets the unit by posting the given unit essence.
   *
   * @param request UnitRequest
   * @return an unit
   * @throws AppException An exception will be thrown if the essence Json is invalid
   */
  @PostMapping("/unit")
  public Unit postUnit(@RequestBody UnitRequest request) {
    try {
      UnitEssenceImpl essence = request.getUnitEssence();
      Unit unit = catalog.postUnit(essence);
      auditLogger.readUnitEssenceSuccess(Collections.singletonList(unit.toString()));
      return unit;
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * Gets a list of the units from the given unit symbol. The symbol of unit is not unique in the
   * catalog.
   *
   * @param symbol unit symbol
   * @return IQueryResult      The list of the unitEssences
   * @throws AppException An exception will be thrown if the symbol is invalid
   */
  @GetMapping("/unit/symbols")
  public QueryResult getUnitsBySymbol(@RequestParam(value = "symbol") String symbol) {
    try {
      QueryResult queryResultForUnits = createQueryResultForUnits(catalog.getUnitsBySymbol(symbol));
      auditLogger
          .readUnitsByUnitSymbolSuccess(Collections.singletonList(queryResultForUnits.toString()));
      return queryResultForUnits;
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * Gets a unit from the given unit symbol which the unit is selected based the ordered namespaces
   * in case there are more than one units having the same given symbol.
   *
   * @param namespaces namespace list in order
   * @param symbol     unit symbol
   * @return a matched unit
   * @throws AppException An exception will be thrown if the symbol is invalid or the symbol does
   *                      exist in the given namespaces.
   */
  @GetMapping("/unit/symbol")
  public Unit getUnitBySymbol(@RequestParam("namespaces") String namespaces,
      @RequestParam("symbol") String symbol) {
    try {
      Unit unit = catalog.getUnitBySymbol(namespaces, symbol);
      auditLogger.readUnitByUnitSymbolSuccess(Collections.singletonList(unit.toString()));
      return unit;
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * Gets the unitEssences of the measurement by posting the given measurement essence Json.
   *
   * @param request MeasurementUnitsRequest
   * @return a list of unitEssences
   * @throws AppException An exception will be thrown if the essence of the measurement is invalid
   */
  @PostMapping("/unit/measurement")
  public QueryResult postUnitsByMeasurement(@RequestBody MeasurementRequest request) {
    try {
      MeasurementEssenceImpl essence = request.getMeasurementEssence();
      QueryResult queryResultForUnits = createQueryResultForUnits(
          catalog.postUnitsByMeasurement(essence));
      auditLogger
          .readUnitByMeasurementSuccess(Collections.singletonList(queryResultForUnits.toString()));
      return queryResultForUnits;
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * Gets the unitEssences of the measurement from the given measurement ancestry.
   *
   * @param ancestry ancestry that could refer to a base measurement or a child measurement
   * @return a list of unitEssences
   * @throws AppException An exception will be thrown if the ancestry of the measurement is invalid
   */
  @GetMapping("/unit/measurement")
  public QueryResult getUnitsByMeasurement(@RequestParam("ancestry") String ancestry) {
    try {
      QueryResult queryResultForUnits = createQueryResultForUnits(
          catalog.getUnitsByMeasurement(ancestry));
      auditLogger
          .readUnitsByMeasurementSuccess(Collections.singletonList(queryResultForUnits.toString()));
      return queryResultForUnits;
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * Gets the preferred unitEssences of the measurement by posting the given measurement essence.
   *
   * @param request MeasurementRequest
   * @return IQueryResult   a list of preferred units
   * @throws AppException An exception will be thrown if the essence of the measurement is invalid
   */
  @PostMapping("/unit/measurement/preferred")
  public QueryResult postPreferredUnitsByMeasurement(@RequestBody MeasurementRequest request) {
    try {
      MeasurementEssenceImpl essence = request.getMeasurementEssence();
      QueryResult queryResultForUnits = createQueryResultForUnits(
          catalog.postPreferredUnitsByMeasurement(essence));
      auditLogger
          .readPreferredUnitsByMeasurementSuccess(
              Collections.singletonList(queryResultForUnits.toString()));
      return queryResultForUnits;
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * Gets the preferred unitEssences of the measurement from the given measurement ancestry.
   *
   * @param ancestry ancestry that could refer to a base measurement or a child measurement
   * @return IQueryResult   a list of preferred units
   * @throws AppException An exception will be thrown if the ancestry of the measurement is invalid
   */
  @GetMapping("/unit/measurement/preferred")
  public QueryResult getPreferredUnitsByMeasurement(@RequestParam("ancestry") String ancestry) {

    try {
      return createQueryResultForUnits(catalog.getPreferredUnitsByMeasurement(ancestry));
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * Gets a unit by posting the given unit system name and measurement essence.
   *
   * @param unitSystemName unit system name
   * @param request        MeasurementRequest
   * @return an unit object
   * @throws AppException An exception will be thrown if the unit system name/essence or measurement
   *                      essence is invalid or there is no unit system for given unit system and
   *                      measurement.
   */
  @PostMapping("/unit/unitsystem")
  public Unit postUnitBySystemAndMeasurement(@RequestParam("unitSystemName") String unitSystemName,
      @RequestBody MeasurementRequest request) {
    try {
      MeasurementEssenceImpl essence = request.getMeasurementEssence();
      return catalog.postUnitBySystemAndMeasurement(unitSystemName, essence);
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * Gets a unit from the given unit system name and measurement ancestry.
   *
   * @param unitSystemName unit system name
   * @param ancestry       measurement ancestry
   * @return an unit object
   * @throws AppException An exception will be thrown if the unit system name/ancestry is invalid or
   *                      there is no unit system for given unit system and measurement.
   */
  @GetMapping("/unit/unitsystem")
  public Unit getUnitBySystemAndMeasurement(@RequestParam("unitSystemName") String unitSystemName,
      @RequestParam("ancestry") String ancestry) {

    try {
      return catalog.getUnitBySystemAndMeasurement(unitSystemName, ancestry);
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * Gets the conversion coefficient in ScaleOffset by posting the fromUnit essence and toUnit
   * essence.
   *
   * @param request ConversionScaleOffsetRequest
   * @return a conversion result that contains result in {@link ScaleOffset} format
   * @throws AppException An exception will be thrown if
   *                      <ul>
   *                          <li>the essence of the fromUnit or toUnit is invalid or;</li>
   *                          <li>fromUnit and toUnit are not convertible.</li>
   *                      </ul>
   */
  @PostMapping("/conversion/scale")
  public ConversionResult postConversionScaleOffset(
      @RequestBody ConversionScaleOffsetRequest request) {
    try {
      UnitEssenceImpl fromUnitEssence = request.getFromUnitEssence();
      UnitEssenceImpl toUnitEssence = request.getToUnitEssence();
      return catalog.postConversionScaleOffset(fromUnitEssence, toUnitEssence);
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * Gets the conversion coefficient in ABCD by posting the fromUnit essence and toUnit essence
   *
   * @param request ConversionABCDRequest
   * @return a conversion result that contains result in {@link ABCD} format
   * @throws AppException An exception will be thrown if
   *                      <ul>
   *                          <li>the essence of the fromUnit or toUnit is invalid or;</li>
   *                          <li>fromUnit and toUnit are not convertible.</li>
   *                      </ul>
   */
  @PostMapping("/conversion/abcd")
  public ConversionResult postConversionABCD(@RequestBody ConversionABCDRequest request) {
    try {
      UnitEssenceImpl fromUnitEssence = request.getFromUnitEssence();
      UnitEssenceImpl toUnitEssence = request.getToUnitEssence();
      return catalog.postConversionABCD(fromUnitEssence, toUnitEssence);
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * Gets the conversion coefficient in ScaleOffset from the fromUnit and toUnit where are selected
   * based the ordered namespaces.
   *
   * @param namespaces namespace list in order
   * @param fromSymbol symbol of the fromUnit
   * @param toSymbol   symbol of the toUnit
   * @return a conversion result that contains result in {@link ScaleOffset} format
   * @throws AppException An exception will be thrown if
   *                      <ul>
   *                          <li>neither fromSymbol nor toSymbol exists in the given namespaces or;</li>
   *                          <li>fromUnit and toUnit are not convertible.</li>
   *                      </ul>
   */
  @GetMapping("/conversion/scale")
  public ConversionResult getConversionScaleOffsetBySymbols(
      @RequestParam("namespaces") String namespaces,
      @RequestParam("fromSymbol") String fromSymbol,
      @RequestParam("toSymbol") String toSymbol) {

    try {
      return catalog.getConversionScaleOffsetBySymbols(namespaces, fromSymbol, toSymbol);
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }

    /********************************************
     UnitSystem related API
     *********************************************/
    /**
     * Gets a list of {@link UnitSystemInfoResponse}
     *
     * @return A list of unit system infos {@link UnitSystemInfoResponse}
     */
	@GetMapping("/unitsystem/list")
    public UnitSystemInfoResponse getUnitSystemInfoList(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit) {
        assertRange(offset, limit);
        return catalog.getUnitSystemInfoList(offset, limit);
    }

    /**
     * Gets a unit system by posting the given unit system essence Json string
     *
     * @param request UnitSystemRequest
     * @return     a unit system
     * @throws AppException An exception will be thrown if the essence of the unit system is invalid
     */
	@PostMapping("/unitsystem")
    public UnitSystem postUnitSystem(@RequestBody UnitSystemRequest request,
                                     @RequestParam(value = "offset", defaultValue = "0") int offset,
                                     @RequestParam(value = "limit", defaultValue = "100") int limit) {
        assertRange(offset, limit);
        try {
            UnitSystemEssenceImpl essence = request.getUnitSystemEssence();
            return catalog.postUnitSystem(essence, offset, limit);
        }
        catch(Exception ex) {
            throw AppException.createBadRequest(ex.getMessage());
        }
    }

    /**
     * Gets a unit system from the given unit system name
     *
     * @param name a unit system name
     * @return     a unit system
     * @throws AppException An exception will be thrown if the name of the unit system is invalid
     */
	@GetMapping("/unitsystem")
    public UnitSystem getUnitSystem(@RequestParam("name") String name,
                                    @RequestParam(value = "offset", defaultValue = "0") int offset,
                                    @RequestParam(value = "limit", defaultValue = "100") int limit) {
        assertRange(offset, limit);
        try {
            return catalog.getUnitSystem(name, offset, limit);
        }
        catch(Exception ex) {
            throw AppException.createBadRequest(ex.getMessage());
        }
    }
  }

  /********************************************
   Search related API
   *********************************************/
  /**
   * Search the unitEssences by keyword and return the results in given range
   *
   * @param request The {@link SearchRequest} containing the query string
   * @param offset  The offset of the first item in the list of all unitEssences. It is optional and
   *                is 0 by default.
   * @param limit   The maximum number of the unitEssences returned. Minus 1("-1") means all. It is
   *                optional and is 100 by default.
   * @return IQueryResult
   * @throws AppException An exception will be thrown if the startIndex is out of the range or the
   *                      keyword is empty or null
   */
  @PostMapping("/unit/search")
  public QueryResult postSearchUnits(@RequestBody SearchRequest request,
      @RequestParam(value = "offset", defaultValue = "0") int offset,
      @RequestParam(value = "limit", defaultValue = "100") int limit) {
    assertRange(offset, limit);
    try {
      return createQueryResultForUnits(catalog.searchUnits(request.getQuery(), offset, limit));
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * Search the unitEssences by keyword and return the results in given range
   *
   * @param request The {@link SearchRequest} containing the query string
   * @param offset  The offset of the first item in the list of all measurements. It is optional and
   *                is 0 by default.
   * @param limit   The maximum number of the measurements returned. Minus 1("-1") means all. It is
   *                optional and is 100 by default.
   * @return IQueryResult
   * @throws AppException An exception will be thrown if the startIndex is out of the range or the
   *                      keyword is empty or null
   */
  @PostMapping("/measurement/search")
  public QueryResult postSearchMeasurements(@RequestBody SearchRequest request,
      @RequestParam(value = "offset", defaultValue = "0") int offset,
      @RequestParam(value = "limit", defaultValue = "100") int limit) {
    assertRange(offset, limit);
    try {
      QueryResultImpl queryResultForMeasurements = Utility.createQueryResultForMeasurements(
          catalog.searchMeasurements(request.getQuery(), offset, limit));
      auditLogger.searchMeasurementsByKeyword(
          Collections.singletonList(queryResultForMeasurements.toString()));
      return queryResultForMeasurements;
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }
  }

  /**
   * POST a Generic search query
   *
   * @param request The {@link SearchRequest} containing the query string
   * @param offset  The offset into the response array
   * @param limit   The limit to the response size
   * @return The {@link QueryResult}
   * @throws AppException An exception will be thrown if the startIndex is out of the range or the
   *                      keyword is empty or null
   */
  @PostMapping("/catalog/search")
  public QueryResult postSearch(@RequestBody SearchRequest request,
      @RequestParam(value = "offset", defaultValue = "0") int offset,
      @RequestParam(value = "limit", defaultValue = "100") int limit) {
    assertRange(offset, limit);
    try {
      return catalog.search(request.getQuery(), offset, limit);
    } catch (Exception ex) {
      throw AppException.createBadRequest(ex.getMessage());
    }

    @GetMapping("/unit/maps")
    public QueryResult getUnitMaps(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit) {
        assertRange(offset, limit);
        try {
            List<UnitMapItem> allItems = new ArrayList<>();
            for (UnitMapImpl unitMap : catalog.getUnitMaps()) {
                allItems.addAll(unitMap.getUnitMapItems());
            }
            QueryResultImpl result = new QueryResultImpl();
            List<UnitMapItem> items = Utility.getRange(allItems, offset, limit);
            for (UnitMapItem item : items) {
                result.addUnitMapItem(item);
            }
            result.setTotalCount(allItems.size());
            result.setOffset(offset);
            return result;
        } catch (IndexOutOfBoundsException ex) {
            throw AppException.createBadRequest(ex.getMessage());
        }
    }

    @GetMapping("/measurement/maps")
    public QueryResult getMeasurementMaps(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit) {
        assertRange(offset, limit);
        try {
            List<MeasurementMapItem> allItems = new ArrayList<>();
            for (MeasurementMapImpl measurementMap : catalog.getMeasurementMaps()) {
                allItems.addAll(measurementMap.getMeasurementMapItems());
            }
            QueryResultImpl result = new QueryResultImpl();
            List<MeasurementMapItem> items = Utility.getRange(allItems, offset, limit);
            for (MeasurementMapItem item : items) {
                result.addMeasurementMapItem(item);
            }
            result.setTotalCount(allItems.size());
            result.setOffset(offset);
            return result;
        } catch (IndexOutOfBoundsException ex) {
            throw AppException.createBadRequest(ex.getMessage());
        }
    }

    @GetMapping("/catalog/mapstates")
    public QueryResult getMapStates(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit) {
        assertRange(offset, limit);
        try {
            QueryResultImpl result = new QueryResultImpl();
            result.setMapStates(Utility.getRange(catalog.getWellknownMapStates(), offset, limit));
            result.setTotalCount(catalog.getWellknownMapStates().size());
            result.setOffset(offset);
            return result;
        } catch (IndexOutOfBoundsException ex) {
            throw AppException.createBadRequest(ex.getMessage());
        }
    }
  }
}
