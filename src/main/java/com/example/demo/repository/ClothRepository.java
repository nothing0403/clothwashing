package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Cloth;
import java.util.List;


@Repository
public interface ClothRepository extends JpaRepository<Cloth, Integer>{
    
	@Query(value = "select * from cloth where cloth_id = :clothId", nativeQuery = true)
	Cloth findByClothId(Integer clothId);
	
	@Query(value = "select * from cloth where cloth_name = :clothName", nativeQuery = true)
	List<Cloth> findByClothName(String clothName);
	
	@Query(value = "select * from cloth", nativeQuery = true)
	List<Cloth> findAll();
}
