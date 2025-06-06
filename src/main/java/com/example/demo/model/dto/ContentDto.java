package com.example.demo.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
