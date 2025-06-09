package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.MapToDto;
import com.example.demo.model.dto.ContentDto;
import com.example.demo.model.entity.Content;
import com.example.demo.model.entity.Receiver;
import com.example.demo.model.entity.Sender;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.Item;
import com.example.demo.repository.ContentRepository;
import com.example.demo.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private ContentRepository contentRepository;
	
	@Autowired
	private MapToDto mapToDto;

	@Override
	public void addContent(User user, Sender sender, Receiver receiver, List<Item> items,
			String receiveTime, String sendTime) {
		Content content = new Content();	
		// 將 user 、 sender 、 receiver 、 item 的物件存放進 content，JPA 會自動生成對應外鍵
		content.setUser(user);
		content.setSender(sender);
		content.setReceiver(receiver);
		content.setItems(items);
		
		// 訂單建立時間
		LocalDateTime dateTime = LocalDateTime.now();
		String dateTimeStr = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		content.setContentBuildDate(dateTimeStr);
		content.setContentFinalDate(sender.getSenderDate());
		content.setContentState(false);
		content.setContentPrice(items.stream().mapToInt(item -> item.getItemPrice()).sum());
		
		contentRepository.save(content);
	}

	@Override
	public List<ContentDto> getContents(String useraccount) {
		
		List<Content> contents = contentRepository.findByUserName(useraccount);
		
		return contents.stream().map(content -> mapToDto.contentToDto(content)).collect(Collectors.toList());
	}

}