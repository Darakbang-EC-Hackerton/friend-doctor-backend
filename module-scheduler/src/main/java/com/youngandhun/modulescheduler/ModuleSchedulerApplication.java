package com.youngandhun.modulescheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ModuleSchedulerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ModuleSchedulerApplication.class, args);
	}
}