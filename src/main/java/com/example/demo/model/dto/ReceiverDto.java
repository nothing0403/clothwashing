package com.example.demo.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverDto {

	@NotNull(message = "{receiverDto.senderName.notNull}")
	@Size(min = 1, message = "{receiverDto.senderName.size}")
    private String receiverName;
	
	@NotNull(message = "{receiverDto.senderPhone.notNull}")
	@Size(min = 1, message = "{receiverDto.senderPhone.size}")
	private String receiverPhone;
	
	@NotNull(message = "{receiverDto.senderAddress.notNull}")
	@Size(min = 1, message = "{receiverDto.senderAddress.size}")
	private String receiverAddress;

}
