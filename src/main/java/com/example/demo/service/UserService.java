package com.example.demo.service;

import com.example.demo.model.dto.UserDto;

public interface UserService {

	public UserDto getUser(Integer id);
	
	public void addUser(String name, String account, String password, 
			            String phone, String address, String role);
	
}
