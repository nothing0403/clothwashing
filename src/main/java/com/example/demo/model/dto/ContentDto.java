package com.example.demo.model.dto;

import jakarta.validation.constraints.NotNull;

public class ContentDto {

	@NotNull(message = "{contentDto.contentBuildTime.notNull}")
	private String contentBuildTime;
	
	@NotNull(message = "{contentDto.receiveTime.notNull}")
	private String receiveTime;
	
	@NotNull(message = "{contentDto.sendTime.notNull}")
	private String sendTime;
	
	@NotNull(message = "{contentDto.contentState.notNull}")
	private boolean contentState;
	
	@NotNull(message = "{contentDto.contentPrice.notNull}")
	private Integer contentPrice;

}
