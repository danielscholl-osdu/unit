package org.opengroup.osdu.unitservice;

import org.opengroup.osdu.core.common.logging.DefaultLogger;
import org.opengroup.osdu.core.common.logging.JaxRsDpsLog;
import org.opengroup.osdu.core.common.model.http.DpsHeaders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UomAksApplication {

  @Bean
  public JaxRsDpsLog getJaxRsDpsLog() {
    return new JaxRsDpsLog(new DefaultLogger(), new DpsHeaders());
  }

  @Bean
  public DpsHeaders getDpsHeaders() {
    return new DpsHeaders();
  }

  public static void main(String[] args) {
    SpringApplication.run(UomAksApplication.class, args);
  }
}
