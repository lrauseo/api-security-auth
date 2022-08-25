package com.lrrauseo.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ApiSecurityAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSecurityAuthApplication.class, args);
	}

}
