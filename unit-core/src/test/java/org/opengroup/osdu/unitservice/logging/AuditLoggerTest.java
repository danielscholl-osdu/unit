/*
 * Copyright 2021 Google LLC
 * Copyright 2021 EPAM Systems, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opengroup.osdu.unitservice.logging;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.opengroup.osdu.core.common.logging.JaxRsDpsLog;
import org.opengroup.osdu.core.common.model.http.DpsHeaders;

public class AuditLoggerTest {

  @Mock
  private JaxRsDpsLog log;

  @InjectMocks
  private AuditLogger sut;

  @Mock
  private DpsHeaders dpsHeaders;


  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    when(dpsHeaders.getUserEmail()).thenReturn("user");
  }

  @Test
  public void should_writeReadCatalogLastModifiedEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.readCatalogLastModifiedSuccess(resource);

    verify(this.log, times(1)).audit(any());
  }

  @Test
  public void should_writeReadCatalogEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.readCatalogSuccess(resource);

    verify(this.log, times(1)).audit(any());
  }

  @Test
  public void should_writeReadSpecificMeasurementEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.readSpecificMeasurementSuccess(resource);

    verify(this.log, times(1)).audit(any());
  }

  @Test
  public void should_writeReadUnitsSuccessEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.readUnitsSuccess(resource);

    verify(this.log, times(1)).audit(any());
  }

  @Test
  public void should_writeReadUnitEssenceEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.readUnitEssenceSuccess(resource);

    verify(this.log, times(1)).audit(any());
  }

  @Test
  public void should_writeReadUnitsByUnitSymbolEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.readUnitsByUnitSymbolSuccess(resource);

    verify(this.log, times(1)).audit(any());
  }

  @Test
  public void should_writeReadUnitByUnitSymbolEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.readUnitByUnitSymbolSuccess(resource);

    verify(this.log, times(1)).audit(any());
  }

  @Test
  public void should_writeReadUnitByMeasurementEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.readUnitByMeasurementSuccess(resource);

    verify(this.log, times(1)).audit(any());
  }

  @Test
  public void should_writeReadUnitsByMeasurementEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.readUnitsByMeasurementSuccess(resource);

    verify(this.log, times(1)).audit(any());
  }

  @Test
  public void should_writeReadMeasurementsEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.readMeasurementsSuccess(resource);

    verify(this.log, times(1)).audit(any());
  }

  @Test
  public void should_writeReadPreferredUnitsByMeasurementEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.readPreferredUnitsByMeasurementSuccess(resource);

    verify(this.log, times(1)).audit(any());
  }

  @Test
  public void should_writeReadUnitSystemEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.readUnitSystemSuccess(resource);

    verify(this.log, times(1)).audit(any());
  }

  @Test
  public void should_writeSearchMeasurementsByKeywordEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.searchMeasurementsByKeyword(resource);

    verify(this.log, times(1)).audit(any());
  }

  @Test
  public void should_writeGetUnitMapsEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.getUnitMapsSuccess(resource);

    verify(this.log, times(1)).audit(any());
  }

  @Test
  public void should_writeGetMeasurementMapsEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.getMeasurementMapsSuccess(resource);

    verify(this.log, times(1)).audit(any());
  }

  @Test
  public void should_writeGetMapStateEvent() {
    List<String> resource = Collections.singletonList("1");
    this.sut.getMapStatesSuccess(resource);

    verify(this.log, times(1)).audit(any());
  }
}

