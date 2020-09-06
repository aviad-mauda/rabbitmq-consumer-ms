package com.harel.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class RabbitMQConsumerApp {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMQConsumerApp.class, args);
	}

}
