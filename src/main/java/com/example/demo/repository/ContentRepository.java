package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Content;
import com.example.demo.model.entity.Receiver;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer>{
    
	@Query(value = "select * from content where user_id = :userId", nativeQuery = true)
	List<Content> getContentByUser(Integer userId);
	
	@Query(value = "select * from content where receiver_id = :receiverId", nativeQuery = true)
	List<Receiver> getReceiverByContent(Integer receiverId);
	
	@Query(value = "select * from content where sender_id = :senderId", nativeQuery = true)
	List<Receiver> getSenderByContent(Integer senderId);
}
