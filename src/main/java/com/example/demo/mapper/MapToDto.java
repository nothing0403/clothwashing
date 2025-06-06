package com.example.demo.mapper;

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

@Component
public class MapToDto {
	
	@Autowired
	private ModelMapper modelMapper;

	public UserDto userToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}
    
	public SenderDto senderToDto(Sender sender) {
		return modelMapper.map(sender, SenderDto.class);
	}
	
	public ReceiverDto receiverToDto(Receiver receiver) {
		return modelMapper.map(receiver, ReceiverDto.class);
	}
	
	public ItemDto itemToDto(Item item) {
		return modelMapper.map(item, ItemDto.class);
	}
	
	public ContentDto contentToDto(Content content) {
		return modelMapper.map(content, ContentDto.class);
	}
	
	public ClothDto clothToDto(Cloth cloth) {
		return modelMapper.map(cloth, ClothDto.class);
	}
}
