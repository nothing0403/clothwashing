package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Sender;
import java.util.List;


@Repository
public interface SenderRepository extends JpaRepository<Sender, Integer>{

	// 從 content 抓取 sender_id 找尋對應的 sender 資料
    @Query(value = "select * from sender where sender_id = :senderId", nativeQuery = true)
    Sender findBySenderId(Integer senderId);
}
