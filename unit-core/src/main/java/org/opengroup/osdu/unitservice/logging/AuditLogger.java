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

import java.util.List;
import java.util.Objects;
import org.opengroup.osdu.core.common.logging.JaxRsDpsLog;
import org.opengroup.osdu.core.common.logging.audit.AuditPayload;
import org.opengroup.osdu.core.common.model.entitlements.Groups;
import org.opengroup.osdu.core.common.model.http.DpsHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class AuditLogger {

  private JaxRsDpsLog logger;
  private DpsHeaders dpsHeaders;
  private AuditEvents events = null;

  public AuditLogger(JaxRsDpsLog logger, DpsHeaders dpsHeaders) {
    this.logger = logger;
    this.dpsHeaders = dpsHeaders;
  }

  private AuditEvents getAuditEvents() {
    if (this.events == null) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (Objects.nonNull(authentication) && authentication.getPrincipal() instanceof Groups) {
        Groups groups = (Groups) authentication.getPrincipal();
        this.events = new AuditEvents(groups.getMemberEmail());
      } else {
        this.events = new AuditEvents(this.dpsHeaders.getUserEmail());
      }
    }
    return this.events;
  }

  private void writeLog(AuditPayload log) {
    this.logger.audit(log);
  }

  public void readCatalogSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getReadCatalogEventSuccess(resources));
  }

  public void readCatalogLastModifiedSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getReadCatalogLastModifiedSuccess(resources));

  }

  public void readMeasurementsSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getReadMeasurementsSuccess(resources));
  }

  public void readSpecificMeasurementSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getReadSpecificMeasurementSuccess(resources));
  }

  public void readUnitsSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getReadUnitsSuccess(resources));
  }

  public void readUnitEssenceSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getReadUnitEssenceSuccess(resources));
  }

  public void readUnitsByUnitSymbolSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getReadUnitsByUnitSymbolSuccess(resources));
  }

  public void readUnitByUnitSymbolSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getReadUnitByUnitSymbolSuccess(resources));
  }

  public void readUnitByMeasurementSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getReadUnitByMeasurementSuccess(resources));
  }

  public void readUnitsByMeasurementSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getReadUnitsByMeasurementSuccess(resources));
  }

  public void readPreferredUnitsByMeasurementSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getReadPreferredUnitsByMeasurementSuccess(resources));
  }

  public void readUnitSystemSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getReadUnitSystemSuccess(resources));
  }

  public void searchMeasurementsByKeyword(List<String> resources) {
    this.writeLog(this.getAuditEvents().getSearchMeasurementsByKeyword(resources));
  }

  public void getUnitMapsSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getUnitMapsSuccess(resources));
  }

  public void getMeasurementMapsSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getMeasurementMapsSuccess(resources));
  }

  public void getMapStatesSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getMapStatesSuccess(resources));
  }

  public void readConversionABCDBySymbolsSuccess(List<String> resources) {
    this.writeLog(this.getAuditEvents().getReadConversionABCDBySymbolsSuccess(resources));
  }
}
