package com.example.web_socket_sample.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.web_socket_sample.constant.RabbitMqConstants;
import com.example.web_socket_sample.dto.ChatMessageDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RabbitMqListeners {
	
	@RabbitListener(queues=RabbitMqConstants.CHAT_QUEUE_NM)
	 public void processQueue1(final ChatMessageDto message) {
		log.info(">> " + message.getMessage());
	}
}
