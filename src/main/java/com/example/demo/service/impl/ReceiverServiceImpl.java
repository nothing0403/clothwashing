package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.MapToDto;
import com.example.demo.model.dto.ReceiverDto;
import com.example.demo.model.entity.Receiver;
import com.example.demo.repository.ReceiverRepository;
import com.example.demo.service.ReceiverService;

@Service
public class ReceiverServiceImpl implements ReceiverService{

	@Autowired
	private ReceiverRepository receiverRepository;
	
	@Autowired
	private MapToDto mapToDto;
	
	@Override
	public ReceiverDto getReceiver(Integer id) {
		return mapToDto.receiverToDto(receiverRepository.findByReceiverId(id));
	}

	@Override
	public void addReceiver(String name, String phone, String address) {
		Receiver receiver = new Receiver();
		receiver.setReceiverName(name);
		receiver.setReceiverPhone(phone);
		receiver.setReceiverAddress(address);
		receiverRepository.save(receiver);
	}

}
