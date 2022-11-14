package com.ups.bookapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication @EnableEurekaClient
public class BookapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookapiApplication.class, args);
	}

}
