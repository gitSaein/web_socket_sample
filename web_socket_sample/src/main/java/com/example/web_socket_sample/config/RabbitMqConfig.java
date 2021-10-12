package com.example.web_socket_sample.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.web_socket_sample.constant.RabbitMqConstants;

@Configuration
public class RabbitMqConfig {
	
	@Bean
	public Queue queue() {
		return new Queue(RabbitMqConstants.CHAT_QUEUE_NM, true);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(RabbitMqConstants.CHAT_EXCHANGE_NM);
	}
	
	@Bean
	public Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(RabbitMqConstants.ROUTING_KEY);
	}
	
	@Bean
	public Jackson2JsonMessageConverter produceJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(produceJackson2MessageConverter());
		return rabbitTemplate;
	}
}
