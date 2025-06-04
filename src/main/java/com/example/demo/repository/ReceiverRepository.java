package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Receiver;
import java.util.List;


@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, Integer>{
    
	// 從 content 抓取 receiver_id 找尋對應的 receiver 資料
	@Query(value = "select * from receiver where receiver_id = :receiverId", nativeQuery = true)
	Receiver findByReceiverId(Integer receiverId);
}
