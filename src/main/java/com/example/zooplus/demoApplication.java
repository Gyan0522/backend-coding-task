package com.example.zooplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition
@SpringBootApplication
public class demoApplication {

	public static void main(String[] args) {
		SpringApplication.run(demoApplication.class, args);
	}

}
