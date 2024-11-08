package com.youngandhun.moduleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.youngandhun.modulecore", "com.youngandhun.modulecommon", "com.youngandhun.moduleapi"})
@EntityScan(basePackages = "com.youngandhun.modulecore")
@EnableJpaRepositories(basePackages = "com.youngandhun.modulecore")
@EnableJpaAuditing
public class ModuleApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ModuleApiApplication.class, args);
	}
}