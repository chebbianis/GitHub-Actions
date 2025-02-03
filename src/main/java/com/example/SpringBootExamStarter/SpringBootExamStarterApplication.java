package com.example.SpringBootExamStarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootExamStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExamStarterApplication.class, args);
	}

}
