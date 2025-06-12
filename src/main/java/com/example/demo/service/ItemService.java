package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ClothDto;
import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.entity.Content;

public interface ItemService {
	
	public void addItem(List<ClothDto> clothDtos, Content content);
	
	public List<ItemDto> getItems(Integer contentId);
	
}
