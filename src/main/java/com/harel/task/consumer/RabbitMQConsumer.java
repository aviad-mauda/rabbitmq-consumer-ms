package com.harel.task.consumer;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RabbitMQConsumer {

	public void recievedMessage(String name) {
		System.out.println("Recieved Message From RabbitMQ: " + name);
		
	    final String uri = "http://localhost:8083/insert";

	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.postForObject(uri, name, String.class);
	    System.out.println(result);
	    
	}
}
