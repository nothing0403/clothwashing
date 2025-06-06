package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
    
	@Query(value = "select * from item where content_id =: contentId", nativeQuery = true)
	List<Item> findAllByContentId(Integer contentId);
	
	@Query(value = "select * from item where cloth_id =: clothId", nativeQuery = true)
	Item findByClothId(Integer clothId);
	
	@Query(value = "select * from item", nativeQuery = true)
	List<Item> findAllItem();
}
