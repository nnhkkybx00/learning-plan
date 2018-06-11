package com.dl.tracezipkinserverelasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class TraceZipkinServerElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraceZipkinServerElasticsearchApplication.class, args);
	}
}
