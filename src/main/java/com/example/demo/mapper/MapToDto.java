package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.ReceiverDto;
import com.example.demo.model.dto.SenderDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Receiver;
import com.example.demo.model.entity.Sender;
import com.example.demo.model.entity.User;

@Component
public class MapToDto {
	
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
}
