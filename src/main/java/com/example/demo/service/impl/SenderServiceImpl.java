package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.MapToDto;
import com.example.demo.model.dto.SenderDto;
import com.example.demo.model.entity.Sender;
import com.example.demo.repository.SenderRepository;
import com.example.demo.service.SenderService;

@Service
public class SenderServiceImpl implements SenderService{

	@Autowired
	private SenderRepository senderRepository;
	
	@Autowired
	private MapToDto mapToDto;
	
	@Override
	public SenderDto getSender(Integer id) {
		
		Sender sender = senderRepository.findBySenderId(id);
		
		if(sender == null) {
			return null;
		}
		
		return mapToDto.senderToDto(senderRepository.findBySenderId(id));
	}

	@Override
	public void addSender(SenderDto senderDto) {
		
		Sender sender = mapToDto.dtoToSender(senderDto);
		
		senderRepository.save(sender);
	}

	

}