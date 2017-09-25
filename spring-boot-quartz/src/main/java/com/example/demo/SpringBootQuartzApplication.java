package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "classpath:dao-context.xml" })
public class SpringBootQuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootQuartzApplication.class, args);
	}
}
