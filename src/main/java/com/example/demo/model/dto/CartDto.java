package com.example.demo.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

	private List<ClothDto> clothDtos;
	
	private SenderDto senderDto;
	
	private ReceiverDto receiverDto;
}
