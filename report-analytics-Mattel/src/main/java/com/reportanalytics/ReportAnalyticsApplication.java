package com.reportanalytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.reportanalytics.*")
@EnableJpaRepositories
public class ReportAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportAnalyticsApplication.class, args);
	}

}
