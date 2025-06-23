package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ClothDto;
import com.example.demo.model.dto.ContentDto;
import com.example.demo.model.dto.ReceiverDto;
import com.example.demo.model.dto.SenderDto;
import com.example.demo.model.dto.UserDto;

public interface ContentService {

	public void addContent(String useraccount, SenderDto senderDto, ReceiverDto receiverDto, List<ClothDto> clothdtos);
	
	public List<ContentDto> getContents(Integer userId);
	
	public List<ContentDto> getContentsByReceiveDate(String receiveDate);
	
	public List<ContentDto> getContentsBySendDate(String sendDate);
	
	public List<ContentDto> getContents();
	
	public void updateContent(List<ContentDto> contentDtos);
}
