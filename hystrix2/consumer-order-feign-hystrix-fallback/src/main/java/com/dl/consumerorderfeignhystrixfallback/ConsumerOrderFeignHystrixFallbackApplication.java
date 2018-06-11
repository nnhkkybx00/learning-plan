package com.dl.consumerorderfeignhystrixfallback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class ConsumerOrderFeignHystrixFallbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerOrderFeignHystrixFallbackApplication.class, args);
	}
}
