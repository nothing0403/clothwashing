package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.MapToDto;
import com.example.demo.model.dto.ClothDto;
import com.example.demo.model.entity.Cloth;
import com.example.demo.repository.ClothRepository;
import com.example.demo.service.ClothService;

@Service
public class ClothServiceImpl implements ClothService{

	@Autowired
	private ClothRepository clothRepository;
	
	@Autowired
	private MapToDto mapToDto;
	
	@Override
	public List<ClothDto> getCloth() {
		List<Cloth> clothes = clothRepository.findAll();
		
		return clothes.stream().map(cloth -> mapToDto.clothToDto(cloth)).collect(Collectors.toList());
	}

}
