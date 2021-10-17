package com.example.web_socket_sample.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.example.web_socket_sample.config.RabbitMqListeners;
import com.example.web_socket_sample.constant.RabbitMqConstants;
import com.example.web_socket_sample.dto.ChatMessageDto;
import com.example.web_socket_sample.service.ChatMessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {
	
	private final ChatMessageService chatMessageService;
	private final RabbitMqListeners rabbitmqListeners;

    private static final Set<String> SESSION_IDS = new HashSet<>();
//    private final SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/chat/send/message/{roomId}")
	@SendTo("/exchange/message.topic/message.room.{roomId}")
	public ChatMessageDto greeting(@Payload ChatMessageDto message, @DestinationVariable("roomId") int roomId) throws InterruptedException {
		log.info(String.format("[%d] %s ", message.getRoomIdx(), message.getMessage()));
		chatMessageService.sendChantMessage(message);
		return message;
	}
	

	@MessageMapping("chat.enter.{roomId}")
	public void enter(ChatMessageDto message, @DestinationVariable("roomId") int roomId){

	}

  	@EventListener(SessionConnectEvent.class)
	public void onConnect(SessionConnectEvent event) {
		String sessionId = event.getMessage().getHeaders().get("simpSessionId").toString();
		SESSION_IDS.add(sessionId);
		log.info("[connect] connections : {}", SESSION_IDS.size());
	}

	@EventListener(SessionDisconnectEvent.class)
	public void onDisconnect(SessionDisconnectEvent event) {
		String sessionId = event.getSessionId();
		SESSION_IDS.remove(sessionId);
		log.info("[disconnect] connections : {}", SESSION_IDS.size());
	}

}
