package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query(value = "select * from user where user_id = :userId", nativeQuery = true)
	User findByUserId(Integer userId);
	
	
}
