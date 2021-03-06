package com.dl.consumerorderfeignhystrixfallbackstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker //监控
public class ConsumerOrderFeignHystrixFallbackStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerOrderFeignHystrixFallbackStreamApplication.class, args);
	}
}
