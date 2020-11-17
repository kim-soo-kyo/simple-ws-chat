package com.test.message.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.message.entity.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Long>{

	List<MessageEntity> findByRoom(String room);
	
}
