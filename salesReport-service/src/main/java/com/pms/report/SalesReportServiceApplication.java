package com.pms.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SalesReportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesReportServiceApplication.class, args);
	}

}
