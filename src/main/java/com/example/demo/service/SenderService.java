package com.example.demo.service;

import com.example.demo.model.dto.SenderDto;

public interface SenderService {

public SenderDto getSender(Integer id);
	
public void addSender(SenderDto senderDto);
}
