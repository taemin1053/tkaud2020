package com.example.StartProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.example.StartProject")
public class StartProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartProjectApplication.class, args);
	}

}
