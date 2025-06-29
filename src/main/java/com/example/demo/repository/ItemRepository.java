package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
    
	@Query(value = "select * from item where content_id = :contentId", nativeQuery = true)
	List<Item> findByContentId(Integer contentId);
	
	@Query(value = "select * from item where cloth_id = :clothId", nativeQuery = true)
	List<Item> findByClothId(Integer clothId);
	
	@Query(value = "select * from item where item_id = :itemId", nativeQuery = true)
	Item findByItemId(Integer itemId);
}
