package com.example.demo.model.dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
	
	@NotNull(message = "{itemDto.itemId.notNull}")
	private Integer itemId;
	
	@NotNull(message = "{itemDto.itemQuantity.notNull}")
	@Range(min = 1, max = 10, message = "{itemDto.itemQuantity.range}")
	private Integer itemQuantity;
	
	@NotNull(message = "{itemDto.itemPrice.notNull}")
	private Integer itemPrice;

	@NotNull(message = "{itemDto.itemState.notNull}")
	private boolean itemState;
	
	private ClothDto clothDto;
}
