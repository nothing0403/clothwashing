package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.mapper.MapToDto;
import com.example.demo.model.dto.SenderDto;
import com.example.demo.model.entity.Sender;
import com.example.demo.repository.SenderRepository;
import com.example.demo.service.SenderService;

public class SenderServiceImpl implements SenderService{

	@Autowired
	private SenderRepository senderRepository;
	
	@Autowired
	private MapToDto mapToDto;
	
	@Override
	public SenderDto getSender(Integer id) {
		return mapToDto.senderToDto(senderRepository.getSender(id));
	}

	@Override
	public void addSender(String name, String phone, String address) {
		Sender sender = new Sender();
		sender.setSenderName(name);
		sender.setSenderPhone(phone);
		sender.setSenderAddress(address);
		senderRepository.save(sender);
	}

	

}
