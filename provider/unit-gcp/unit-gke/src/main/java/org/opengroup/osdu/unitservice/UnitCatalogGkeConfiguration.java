package org.opengroup.osdu.unitservice;

import org.opengroup.osdu.unitservice.v2.model.CatalogImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;

@Configuration
public class UnitCatalogGkeConfiguration {

    @Bean
    public CatalogImpl catalogImpl(@Value("${osdu.unit.catalog.filename}") String location) throws Exception {
        try (FileReader reader = new FileReader(location)) {
            return CatalogImpl.createCatalog(reader);
        }
    }
}
