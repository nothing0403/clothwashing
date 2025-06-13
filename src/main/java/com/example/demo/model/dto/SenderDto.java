package com.example.demo.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SenderDto {

	@NotNull(message = "{senderDto.senderName.notNull}")
	@Size(min = 1, message = "{senderDto.senderName.size}")
    private String senderName;
	
	@NotNull(message = "{senderDto.senderPhone.notNull}")
	@Size(min = 1, message = "{senderDto.senderPhone.size}")
	private String senderPhone;
	
	@NotNull(message = "{senderDto.senderAddress.notNull}")
	@Size(min = 1, message = "{senderDto.senderAddress.size}")
	private String senderAddress; 
	
	@NotNull(message = "{senderDto.senderDate.notNull}")
	@Size(min = 1, message = "{senderDto.senderDate.size}")
	private String senderDate;
	
	@NotNull(message = "{senderDto.senderTimePeriod.notNull}")
	@Size(min = 1, message = "{senderDto.senderTimePeriod.size}")
	private String senderTimePeriod;

}
