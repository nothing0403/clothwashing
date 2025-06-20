package com.example.demo.mapper;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.ClothDto;
import com.example.demo.model.dto.ContentDto;
import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.dto.ReceiverDto;
import com.example.demo.model.dto.SenderDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Cloth;
import com.example.demo.model.entity.Content;
import com.example.demo.model.entity.Item;
import com.example.demo.model.entity.Receiver;
import com.example.demo.model.entity.Sender;
import com.example.demo.model.entity.User;
import com.example.demo.repository.ContentRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Component
public class MapToDto {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ContentRepository contentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public UserDto userToDto(User user) {
    	
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
		userDto.setContentDtos((user.getContents()).stream().map(content -> contentToDto(content)).collect(Collectors.toList()));
		
		return userDto;
	}
	public User dtoToUser(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}
    
	public SenderDto senderToDto(Sender sender) {
		return modelMapper.map(sender, SenderDto.class);
	}
	public Sender dtoToSender(SenderDto senderdto) {
		return modelMapper.map(senderdto, Sender.class); 
	}
	
	public ReceiverDto receiverToDto(Receiver receiver) {
		return modelMapper.map(receiver, ReceiverDto.class);
	}
	public Receiver dtoToReceiver(ReceiverDto receiverDto) {
		return modelMapper.map(receiverDto, Receiver.class);
	}
	
	public ItemDto itemToDto(Item item) {
		
		ItemDto itemDto = modelMapper.map(item, ItemDto.class);
		
		itemDto.setClothDto(clothToDto(item.getCloth()));
		
		return itemDto;
	}
	
	public Item dtoToItem(ItemDto itemDto) {
		return modelMapper.map(itemDto, Item.class);
	}
	
	public ContentDto contentToDto(Content content) {
		
		ContentDto contentDto = modelMapper.map(content, ContentDto.class);
		
		contentDto.setItemDtos((content.getItems()).stream().map(item -> itemToDto(item)).collect(Collectors.toList()));
		
		contentDto.setReceiverDto(receiverToDto(content.getReceiver()));
		
		contentDto.setSenderDto(senderToDto(content.getSender()));
		
		return contentDto;
	}
	
	public Content dtoToContent(ContentDto contentDto) {
		return modelMapper.map(contentDto, Content.class);
	}
	
	public ClothDto clothToDto(Cloth cloth) {
		return modelMapper.map(cloth, ClothDto.class);
	}
	public Cloth dtoToCloth(ClothDto clothDto) {
		return modelMapper.map(clothDto, Cloth.class);
	}
}
