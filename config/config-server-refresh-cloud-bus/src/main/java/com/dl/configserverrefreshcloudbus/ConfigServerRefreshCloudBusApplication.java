package com.dl.configserverrefreshcloudbus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerRefreshCloudBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerRefreshCloudBusApplication.class, args);
	}
}
