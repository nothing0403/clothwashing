package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.MapToDto;
import com.example.demo.model.dto.ClothDto;
import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.entity.Cloth;
import com.example.demo.model.entity.Content;
import com.example.demo.model.entity.Item;
import com.example.demo.repository.ClothRepository;
import com.example.demo.repository.ContentRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ClothRepository clothRepository;
	
	@Autowired
	private ContentRepository contentRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private MapToDto mapToDto;

	@Override
	public void addItem(List<ClothDto> clothDtos, Content content) {
	
		clothDtos.stream().forEach(clothDto -> {
			Item item = new Item();
			item.setItemPrice(clothDto.getClothPrice()*clothDto.getClothQuantity());
			item.setClothQuantity(clothDto.getClothQuantity());
			
			Cloth cloth = mapToDto.dtoToCloth(clothDto);
			
			item.setCloth(cloth);
			item.setContent(content);
			item.setItemState(false);
			
			itemRepository.save(item);
		});
	}

	@Override
	public List<ItemDto> getItems(Integer contentId) {
		
		List<Item> items = itemRepository.findByContentId(contentId);
		
		return items.stream().map(item -> mapToDto.itemToDto(item)).collect(Collectors.toList());
		
	}

}
