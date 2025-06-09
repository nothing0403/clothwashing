package com.example.demo.service;

import com.example.demo.model.dto.ReceiverDto;

public interface ReceiverService {

	public ReceiverDto getReceiver(Integer id);
	
	public void addReceiver(String name, String phone, String address, String date);

}
