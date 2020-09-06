package com.harel.task.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.harel.task.consumer.RabbitMQConsumer;

@Configuration
public class RabbitMQConfig {

	@Value("${harel.rabbitmq.queue}")
	String queueName;

	@Value("${spring.rabbitmq.username}")
	String username;

	@Value("${spring.rabbitmq.password}")
	private String password;

	@Value("${harel.rabbitmq.exchange}")
	private String topicExchangeName;

	@Value("${harel.rabbitmq.routingkey}")
	private String routingKey;
	
	@Bean
	 Queue queue() {
	  return new Queue(queueName, true);
	 }
	 @Bean
	 DirectExchange exchange() {
	  return new DirectExchange(topicExchangeName);
	 }

	  @Bean
	  Binding binding(Queue queue, DirectExchange exchange) {
	    return BindingBuilder.bind(queue).to(exchange).with(topicExchangeName);
	  }
	  
	 @Bean
	 SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
	  MessageListenerAdapter listenerAdapter) {
	  SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	  container.setConnectionFactory(connectionFactory);
	  container.setQueueNames(queueName);
	  container.setMessageListener(listenerAdapter);
	  return container;
	 }
    
	  @Bean
	  MessageListenerAdapter listenerAdapter(RabbitMQConsumer receiver) {
	    return new MessageListenerAdapter(receiver, "recievedMessage");
	  }
	
}
