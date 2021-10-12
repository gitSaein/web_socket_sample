package com.example.web_socket_sample.controller;

import java.util.HashSet;
import java.util.Set;

import com.example.web_socket_sample.dto.ChatDto;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ChatController {

    private static final Set<String> SESSION_IDS = new HashSet<>();
//    private final SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/chat/{roomSeq}")
	@SendTo("/chat/1")
	public ChatDto greeting(@DestinationVariable("roomSeq") int roomSeq, @Payload ChatDto message) throws InterruptedException {
		log.info(String.format("[%d] %s ", roomSeq, message.getMessage()));
		return message;
	}

	@MessageMapping("chat.enter.{roomId}")
	public void enter(ChatDto message, @DestinationVariable("roomId") int roomId){

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
