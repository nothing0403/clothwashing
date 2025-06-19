package com.example.demo.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	@NotNull(message = "{userDto.userName.notNull}")
	@Size(min = 1, message = "{userDto.userName.size}")
	private String userName;
	
	@NotNull(message = "{userDto.userAccount.notNull}")
	@Size(min = 1, message = "{userDto.userAccount.size}")
	private String userAccount;

	@NotNull(message = "{userDto.userPassword.notNull}")
	@Size(min = 1, max = 50, message = "{userDto.userPassword.size}")
	private String userPassword;
	
	@NotNull(message = "{userDto.userPhone.notNull}")
	@Size(min = 1, max = 50, message = "{userDto.userPhone.size}")
	private String userPhone;
	
	@NotNull(message = "{userDto.userAddress.notNull}")
	@Size(min = 1, max = 50, message = "{userDto.userAddress.size}")
	private String userAddress;
	
	@NotNull(message = "{userDto.userRole.notNull}")
	@Size(min = 1, max = 50, message = "{userDto.userRole.size}")
	private String userRole;
}
