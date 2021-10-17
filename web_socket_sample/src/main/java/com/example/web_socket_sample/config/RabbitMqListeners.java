package com.example.web_socket_sample.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.example.web_socket_sample.constant.RabbitMqConstants;
import com.example.web_socket_sample.dto.ChatMessageDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMqListeners {
	
	private final RabbitTemplate rabbitTemplate;

	
	@RabbitListener(queues=RabbitMqConstants.CHAT_QUEUE_NM)
	 public void processQueue1(final ChatMessageDto message) {
		log.info(">> " + message.getMessage());
		rabbitTemplate.convertAndSend("/exchagne/message.topic/message.room." + message.getRoomIdx(), message);

//		return message;
	}
	
//	@RabbitListener(bindings = @QueueBinding(
//	value = @Queue,
//	exchange = @Exchange(value = RabbitMqConstants.CHAT_EXCHANGE_NM),
//	key = ""
//	))
//public void processQueue2(ChatMessageDto message) {
//log.info(">> " + message);
//}
}
