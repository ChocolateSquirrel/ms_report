package report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages =  "report.proxy")
public class MsReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReportApplication.class, args);
	}

}
