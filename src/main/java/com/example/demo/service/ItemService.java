package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.entity.Cloth;

public interface ItemService {
	
	public void addItem(Integer quantity, Integer clothId, Integer contentId);
	
	public List<ItemDto> getItems(Integer contentId);
	
}
