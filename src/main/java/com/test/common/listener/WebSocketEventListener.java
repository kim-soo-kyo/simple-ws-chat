package com.test.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.test.message.bean.Message;
import com.test.message.bean.Message.MessageType;


@Component
public class WebSocketEventListener {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
	
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		logger.info("Received a new web socket connection");
	}
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		
		String userName = (String)headerAccessor.getSessionAttributes().get("userName");
		String room = (String)headerAccessor.getSessionAttributes().get("room");
	
		if (userName != null) {
			logger.info("User Disconnected {}", userName);
			
			Message msg = new Message();
			msg.setType(MessageType.LEAVE);
			msg.setSender(userName);
			
			messagingTemplate.convertAndSend("/topic/" + room, msg);
		}
	}
}
