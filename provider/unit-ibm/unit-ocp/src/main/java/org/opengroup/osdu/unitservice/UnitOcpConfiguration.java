package org.opengroup.osdu.unitservice;

import org.opengroup.osdu.unitservice.v2.model.CatalogImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.util.logging.Logger;

@Configuration
public class UnitOcpConfiguration {
    private static Logger logger = Logger.getLogger(UnitOcpConfiguration.class.getName());

    @Bean
    public CatalogImpl catalogImpl(@Value("${osdu.unit.catalog.filename}") String location) throws Exception {
        logger.info("osdu.unit.catalog.filename=" + location);
        try (FileReader reader = new FileReader(location)) {
            return CatalogImpl.createCatalog(reader);
        }
    }
}
