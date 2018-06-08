package com.dl.configclienteureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConfigClientEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientEurekaApplication.class, args);
	}
}
