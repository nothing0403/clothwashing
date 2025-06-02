package com.example.demo.service;

import com.example.demo.model.dto.UserDto;

public interface UserService {

	public UserDto getUser();
	public void addUser(String name, String password, String account, 
			            String phone, String Address, String role);
}
