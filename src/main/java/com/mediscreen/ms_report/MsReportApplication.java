package com.mediscreen.ms_report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages =  "com.mediscreen.ms_report.proxy")
public class MsReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReportApplication.class, args);
	}

}
