package com.example.web_socket_sample.service;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.web_socket_sample.constant.RabbitMqConstants;
import com.example.web_socket_sample.dto.ChatMessageDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
	
	private final RabbitTemplate rabbitTemplate;
	
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
	
	public void sendChantMessage(ChatMessageDto chat) {
		String key = StringUtils.replace(RabbitMqConstants.ROUTING_KEY, "#", Integer.toString(chat.getRoomIdx()));
		rabbitTemplate.convertAndSend(RabbitMqConstants.CHAT_EXCHANGE_NM, key, chat);
	}
	
	public void receivedChantMessage(ChatMessageDto chat) {
		rabbitTemplate.convertAndSend(RabbitMqConstants.CHAT_EXCHANGE_NM, RabbitMqConstants.ROUTING_KEY + chat.getRoomIdx(), chat);
	}
}
