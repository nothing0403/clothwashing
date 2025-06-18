package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ClothDto;
import com.example.demo.model.dto.ReceiverDto;
import com.example.demo.model.dto.SenderDto;

public interface EmailService {

	public boolean sendEmail(String userAccount, SenderDto senderDto, ReceiverDto receiverDto, List<ClothDto>clothDtos);
}
