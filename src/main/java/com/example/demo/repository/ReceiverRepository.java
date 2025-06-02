package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Receiver;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, Integer>{
    
	@Query(value = "select * from receiver where receiver_id =: receiverId", nativeQuery = true)
	Receiver getReceiver(Integer receiverId);
}
