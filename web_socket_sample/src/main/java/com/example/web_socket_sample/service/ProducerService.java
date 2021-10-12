package com.example.web_socket_sample.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.web_socket_sample.constant.RabbitMqConstants;
import com.example.web_socket_sample.dto.ChatMessageDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProducerService {
	
	private final RabbitTemplate rabbitTemplate;
	
	public void sendChantMessage(ChatMessageDto chat) {
		rabbitTemplate.convertAndSend(RabbitMqConstants.CHAT_EXCHANGE_NM, RabbitMqConstants.ROUTING_KEY + chat.getRommIdx(), chat);
	}
}
