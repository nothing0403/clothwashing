package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ClothDto;

public interface ClothService {

	public List<ClothDto> getClothes(String clothname);
	
	public List<ClothDto> getAllClothes();

}
