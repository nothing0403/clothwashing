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

	@NotNull(message = "{receiverDto.receiverName.notNull}")
	@Size(min = 1, message = "{receiverDto.receiverName.size}")
    private String receiverName;
	
	@NotNull(message = "{receiverDto.receiverPhone.notNull}")
	@Size(min = 1, message = "{receiverDto.receiverPhone.size}")
	private String receiverPhone;
	
	@NotNull(message = "{receiverDto.receiverAddress.notNull}")
	@Size(min = 1, message = "{receiverDto.receiverAddress.size}")
	private String receiverAddress;
	
	@NotNull(message = "{receiverDto.receiverDate.notNull}")
	@Size(min = 1, message = "{receiverDto.receiverDate.size}")
	private String receiverDate;

}
