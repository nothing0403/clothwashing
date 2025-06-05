package com.example.demo.service;

import com.example.demo.exception.PasswordErrorException;
import com.example.demo.exception.UserNoFindException;
import com.example.demo.model.dto.UserDto;

public interface UserService {

	public UserDto getUser(String useraccount, String userpassword) throws UserNoFindException, PasswordErrorException;
	
	public void addUser(String name, String email, String password, 
			            String phone, String address);
	
	public void logoutUser(UserDto userDto);
}
