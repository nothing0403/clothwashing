package com.example.demo.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClothDto {
	
	@NotNull(message = "{clothDto.clothId.notNull}")
	private Integer clothId;

	@NotNull(message = "{clothDto.clothName.notNull}")
	private String clothName;
	
	@NotNull(message = "{clothDto.clothDescription.notNull}")
	private String clothDescription;
	
	@NotNull(message = "{clothDto.clothImg.notNull}")
	private String clothImg;
	
	@NotNull(message = "{clothDto.clothKind.notNull}")
	private String clothKind;
	
	@NotNull(message = "{clothDto.clothPrice.notNull}")
	private Integer clothPrice;
	
	@NotNull(message = "{clothDto.clothQuantity.notNull}")
	private Integer clothQuantity = 0;

	/*@NotNull(message = "{clothDto.itemPrice.notNull}")
	private Integer ItemPrice = 0;*/
}
