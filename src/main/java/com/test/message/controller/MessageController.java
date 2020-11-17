package com.test.message.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.message.bean.Message;
import com.test.message.service.MessageService;

@Controller
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@Autowired
	private MessageService service;
		
	@MessageMapping("/chat/{room}/msg")
	//@SendTo("/topic/public")
	public void sendMessage(@DestinationVariable String room, @Payload Message msg) {
		
		logger.debug(msg.toString());
		
		messagingTemplate.convertAndSend("/topic/" + room, msg);
		service.createMessage(msg);
		//return msg;
	}

	@MessageMapping("/chat/{room}/user")
	//@SendTo("/topic/public")
	public void addUser(@DestinationVariable String room, @Payload Message msg, SimpMessageHeaderAccessor headerAccessor) {
		
		logger.debug(msg.toString());
		
		headerAccessor.getSessionAttributes().put("userName", msg.getSender());
		headerAccessor.getSessionAttributes().put("room", msg.getRoom());
		messagingTemplate.convertAndSend("/topic/" + room, msg);
		//return msg;
	}
	
	@GetMapping("/chat/{room}")
	public @ResponseBody List<Message> getMessageList(@PathVariable("room") String room) {
		return service.getMessageList(room);
	}
	
}
