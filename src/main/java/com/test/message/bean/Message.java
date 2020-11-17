package com.test.message.bean;

import lombok.Data;

@Data
public class Message {

	private MessageType type;
	private String room;
    private String content;
    private String sender;
	
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}

