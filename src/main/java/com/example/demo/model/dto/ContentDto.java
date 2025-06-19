package com.example.demo.model.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDto {

	@NotNull(message = "{contentDto.contentId.notNull}")
	private Integer contentId;
	
	@NotNull(message = "{contentDto.contentBuildDate.notNull}")
	private String contentBuildDate;
	
	@NotNull(message = "{contentDto.contentReceiveDate.notNull}")
	private String contentReceiveDate;
	
	@NotNull(message = "{contentDto.contentSendDate.notNull}")
	private String contentSendDate;
	
	@NotNull(message = "{contentDto.contentState.notNull}")
	private boolean contentState;
	
	@NotNull(message = "{contentDto.contentPrice.notNull}")
	private Integer contentPrice;

	private SenderDto senderDto;
	
	private ReceiverDto receiverDto;
	
	private List<ItemDto> itemDtos;
}
