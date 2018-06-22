package com.dl.validatetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ValidateTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidateTestApplication.class, args);
	}
}
