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
import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.dto.ReceiverDto;
import com.example.demo.model.dto.SenderDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Content;
import com.example.demo.model.entity.Receiver;
import com.example.demo.model.entity.Sender;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.Item;
import com.example.demo.repository.ContentRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.ReceiverRepository;
import com.example.demo.repository.SenderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ContentService;
import com.example.demo.service.ItemService;

import jakarta.transaction.Transactional;

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
	private ItemRepository itemRepository;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private MapToDto mapToDto;

	@Override
	@Transactional
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
		content.setContentReceiveDate(sender.getSenderDate());
		content.setContentSendDate(receiver.getReceiverDate());
		content.setContentState(false);
		content.setContentPrice(clothDtos.stream().mapToInt(clothDto -> clothDto.getClothPrice()*clothDto.getClothQuantity()).sum());
		
		itemService.addItem(clothDtos, contentRepository.save(content));
	}

	@Override
	public List<ContentDto> getContents(Integer userId) {
		
		List<Content> contents = contentRepository.findByUserId(userId);
		
		return contents.stream().map(content -> mapToDto.contentToDto(content)).collect(Collectors.toList());
	}

	@Override
	public List<ContentDto> getContents() {
		
		List<Content> contents = contentRepository.findAll();
		
		return contents.stream().map(content -> mapToDto.contentToDto(content)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<ContentDto> getContentsByReceiveDate(String receiveDate) {
		
        List<Content> contents = contentRepository.findByContentReceiveDate(receiveDate);
		
		return contents.stream().map(content -> mapToDto.contentToDto(content)).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public List<ContentDto> getContentsBySendDate(String sendDate) {
		
        List<Content> contents = contentRepository.findByContentSendDate(sendDate);
		
		return contents.stream().map(content -> mapToDto.contentToDto(content)).collect(Collectors.toList());
	}
	
	public void updateContent(List<ContentDto> contentDtos) {
		
		for(ContentDto contentDto: contentDtos) {
			
			Content content = contentRepository.findByContentId(contentDto.getContentId());
			
			content.setContentState(contentDto.isContentState());
			
			List<ItemDto>itemDtos = contentDto.getItemDtos();
			
			for(ItemDto itemDto: itemDtos) {
				Item item = itemRepository.findByItemId(itemDto.getItemId());
				
				item.setItemState(itemDto.isItemState());
				
				itemRepository.save(item);
			}
			
			contentRepository.save(content);
		}
	}

}