package com.example.demo.service;

import com.example.demo.exception.PasswordErrorException;
import com.example.demo.exception.UserNoFindException;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;

public interface UserService {

	public UserDto getUser(String useraccount, String userpassword) throws UserNoFindException, PasswordErrorException;
	
	public User getUser(String useraccount);
	
	public boolean updateUser(String useraccount, String userpassword) throws UserNoFindException, PasswordErrorException;
	
	public void addUser(String name, String email, String password, 
			            String phone, String address, String role, String token);
	
	public void addEmployee(String name, String email, String password, 
            String phone, String address, String role);
	
	public boolean verifyUser(String token);
	
	public void logoutUser(UserDto userDto);
}
