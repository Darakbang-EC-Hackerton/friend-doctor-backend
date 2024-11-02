package com.youngandhun.modulescheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EntityScan(basePackages = "com.youngandhun.modulecore")
@EnableJpaRepositories(basePackages = "com.youngandhun.modulecore")
public class ModuleSchedulerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ModuleSchedulerApplication.class, args);
	}
}