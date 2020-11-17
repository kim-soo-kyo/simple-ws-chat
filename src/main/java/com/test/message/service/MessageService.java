package com.test.message.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.message.bean.Message;
import com.test.message.entity.MessageEntity;
import com.test.message.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	public void createMessage(Message msg) {
		repository.save(mapper.map(msg, MessageEntity.class));
	}

	public List<Message> getMessageList(String room) {
		return convertEntityToBeanList(repository.findByRoom(room), Message.class);
	}
	
	private <S, T> List<T> convertEntityToBeanList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> mapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}
}
