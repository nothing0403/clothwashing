package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Sender;

@Repository
public interface SenderRepository extends JpaRepository<Sender, Integer>{

    @Query(value = "select * from sender where sender_id = :senderId", nativeQuery = true)
    Sender getSender(Integer senderId);
}
