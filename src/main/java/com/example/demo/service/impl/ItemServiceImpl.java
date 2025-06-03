package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Cloth;
import com.example.demo.model.entity.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public Integer getItemPrice(Cloth cloth) {
		
		Item item = itemRepository.findByClothId(cloth.getClothId());
		
		return cloth.getClothPrice()*item.getItemQuantity();
	}

}
