package org.opengroup.osdu.unitservice;

import com.google.cloud.ServiceOptions;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import org.opengroup.osdu.unitservice.model.CatalogImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class UnitCatalogGaeConfiguration {

    private static final Logger log = Logger.getLogger(UnitCatalogGaeConfiguration.class.getName());

    @Bean
    public CatalogImpl createV2Catalog(@Value("${osdu.unit.gae.catalog.bucket}") String bucketName,
                                        @Value("${osdu.unit.gae.catalog.file}") String catalogFileName) throws Exception {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        if (StringUtils.isEmpty(bucketName)) {
            log.info("UNIT_CATALOG_BUCKET is not provided");
            String projectId = ServiceOptions.getDefaultProjectId();
            bucketName = projectId + "-unit-catalogs";
        }
        log.log(Level.INFO, "Loading unit catalogs from bucketName={0}", bucketName);
        Blob blob = storage.get(bucketName, catalogFileName);
        if (blob == null) {
            throw new IOException("Unit catalog=" + catalogFileName + " not found at bucketName=" + bucketName);
        }
        Reader reader = new InputStreamReader(new ByteArrayInputStream(blob.getContent()));
        log.info("Start creating the catalog");
        CatalogImpl catalogImpl = CatalogImpl.createCatalog(reader);
        log.info("Finished creating the catalog");

        return catalogImpl;
    }

}
