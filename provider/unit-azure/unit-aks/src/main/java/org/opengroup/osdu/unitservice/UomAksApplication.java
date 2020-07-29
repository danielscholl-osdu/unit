package org.opengroup.osdu.unitservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.opengroup.osdu.unitservice"})
public class UomAksApplication {

	public static void main(String[] args) {
		SpringApplication.run(UomAksApplication.class, args);
	}
}
