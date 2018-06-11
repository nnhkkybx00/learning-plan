package com.dl.tracezipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class TraceZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraceZipkinServerApplication.class, args);
	}
}
