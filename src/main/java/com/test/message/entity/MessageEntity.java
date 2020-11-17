package com.test.message.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "message")
public class MessageEntity {

	@Id
	@Column(name="message_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messageSeq;
	@Column
	private String room;
	@Column
    private String content;
	@Column
    private String sender;
    
}

