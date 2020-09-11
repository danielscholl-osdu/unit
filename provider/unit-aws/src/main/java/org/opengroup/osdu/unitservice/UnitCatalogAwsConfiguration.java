// Copyright © 2020 Amazon Web Services
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.opengroup.osdu.unitservice;

// import org.opengroup.osdu.core.aws.s3.S3Config;
// import com.amazonaws.services.s3.AmazonS3;
// import com.amazonaws.services.s3.model.S3Object;
// import com.amazonaws.services.s3.model.GetObjectRequest;

import org.opengroup.osdu.unitservice.model.CatalogImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.FileReader;

@Configuration
public class UnitCatalogAwsConfiguration {

    private static final Logger log = Logger.getLogger(UnitCatalogAwsConfiguration.class.getName());

    @Bean
    public CatalogImpl catalogImpl(@Value("${osdu.unit.catalog.filename}") String location) throws Exception {
        try (FileReader reader = new FileReader(location)) {
            return CatalogImpl.createCatalog(reader);
        }
    }

   

    /**
     * Note: This code is for if we swtich to an S3 implementation.  
     * Common implementation for now is to use the included Units file rather than allow custom ones. 
     * */ 

    // @Value("${aws.region}")
    // private String s3Region;

    // @Value("${aws.s3.endpoint}")
    // private String s3Endpoint;

    // private AmazonS3 s3;

    // @Bean
    // public CatalogImpl createV2Catalog(@Value("${osdu.unit.aws.catalog.bucket}") String bucketName,
    //                                    @Value("${osdu.unit.aws.catalog.file}") String catalogFileName) throws Exception {

    //     if (StringUtils.isEmpty(bucketName)) {
    //         log.info("UNIT_CATALOG_BUCKET is not provided");
    //         bucketName = "dev-unit-service-catalog-bucket";
    //     }
    //     log.log(Level.INFO, "Loading unit catalogs from bucketName={0}", bucketName);
    //     S3Config config = new S3Config(s3Endpoint, s3Region);
    //     s3 = config.amazonS3();
    //     S3Object s3object = s3.getObject(new GetObjectRequest(bucketName, catalogFileName));
    //     Reader reader = new InputStreamReader(s3object.getObjectContent());
    //     log.info("Start creating the catalog");
    //     CatalogImpl catalogImpl = CatalogImpl.createCatalog(reader);
    //     log.info("Finished creating the catalog");


    //     return catalogImpl;
    // }

    

}
