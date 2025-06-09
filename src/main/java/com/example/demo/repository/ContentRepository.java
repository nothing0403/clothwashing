package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Content;
import com.example.demo.model.entity.Receiver;
import com.example.demo.model.entity.Sender;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer>{
    
	@Query(value = "select * from content where user_id = :userId", nativeQuery = true)
	List<Content> findByUserId(Integer userId);
	
	@Query(value = "select * from content where user_name = :userName", nativeQuery = true)
	List<Content> findByUserName(String userName);
	
	@Query(value = "select * from content where receiver_id = :receiverId", nativeQuery = true)
	Content findByReceiverId(Integer receiverId);
	
	@Query(value = "select * from content where sender_id = :senderId", nativeQuery = true)
	Content findBySenderId(Integer senderId);
	
	@Query(value = "select * from content where content_id = :contentId", nativeQuery = true)
	Content findByContentId(Integer contentId);
}
