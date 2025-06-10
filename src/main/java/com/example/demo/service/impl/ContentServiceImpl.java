package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.MapToDto;
import com.example.demo.model.dto.ClothDto;
import com.example.demo.model.dto.ContentDto;
import com.example.demo.model.dto.ReceiverDto;
import com.example.demo.model.dto.SenderDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Content;
import com.example.demo.model.entity.Receiver;
import com.example.demo.model.entity.Sender;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.Item;
import com.example.demo.repository.ContentRepository;
import com.example.demo.repository.ReceiverRepository;
import com.example.demo.repository.SenderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ContentService;
import com.example.demo.service.ItemService;

@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private ContentRepository contentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReceiverRepository receiverRepository;
	
	@Autowired
	private SenderRepository senderRepository;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private MapToDto mapToDto;

	@Override
	public void addContent(String useraccount, SenderDto senderDto, ReceiverDto receiverDto, List<ClothDto> clothDtos) {
		Content content = new Content();	
		// 將 user 、 sender 、 receiver 的物件存放進 content，JPA 會自動生成對應外鍵
		
		User user = userRepository.findByUserAccount(useraccount);
		Sender sender = senderRepository.save(mapToDto.dtoToSender(senderDto));
		Receiver receiver = receiverRepository.save(mapToDto.dtoToReceiver(receiverDto));
		
		content.setUser(user);
		content.setSender(sender);
		content.setReceiver(receiver);
		
		// 訂單建立時間
		LocalDateTime dateTime = LocalDateTime.now();
		String dateTimeStr = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		content.setContentBuildDate(dateTimeStr);
		content.setContentFinalDate(sender.getSenderDate());
		content.setContentState(false);
		content.setContentPrice(clothDtos.stream().mapToInt(clothDto -> clothDto.getClothPrice()*clothDto.getClothQuantity()).sum());
		
		contentRepository.save(content);
		
		clothDtos.stream().forEach(clothDto -> itemService.addItem(clothDto, contentRepository.findByContentBuildDate(dateTimeStr)));
	}

	@Override
	public List<ContentDto> getContents(String useraccount) {
		
		List<Content> contents = contentRepository.findByUserAccount(useraccount);
		
		return contents.stream().map(content -> mapToDto.contentToDto(content)).collect(Collectors.toList());
	}

}