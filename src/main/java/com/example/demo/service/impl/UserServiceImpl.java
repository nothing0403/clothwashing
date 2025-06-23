package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.exception.PasswordErrorException;
import com.example.demo.exception.UserNoFindException;
import com.example.demo.mapper.MapToDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.Hash;

import jakarta.transaction.Transactional;

@Service
// 透過 repository 抓資料
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MapToDto mapToDto;
	
	@Override
	@Transactional
	public UserDto getUser(String useraccount, String userpassword) throws UserNoFindException, PasswordErrorException{
		// useraccount 是唯一值
		User user = userRepository.findByUserAccount(useraccount);
		if(user == null) {
			throw new UserNoFindException("查無此人");
		}
		
		String passwordHash = Hash.getHash(userpassword, user.getUserSalt());
		if(!passwordHash.equals(user.getUserPassword())) {
			throw new PasswordErrorException("密碼錯誤");
		}
		
		user.setUserActive(true);
		userRepository.save(user);
		
		// 將 user 轉成 userDTO 並回傳
		return mapToDto.userToDto(user);
	}
	
	@Override
	@Transactional
	public boolean updateUser(String useraccount, String userpassword) throws UserNoFindException, PasswordErrorException{
		// useraccount 是唯一值
		
		User user = userRepository.findByUserAccount(useraccount);
		
		if(user == null) {
			return false;
		}
		else {
			String salt = Hash.getSalt();
			user.setUserSalt(salt);
			String passwordHash = Hash.getHash(userpassword, salt);
			user.setUserPassword(passwordHash);
			
			return true;
		}
	}
	

	@Override
	public void addUser(String name, String account, String password, String phone, String address, String role ) {
		
		User user = new User();
		
		user.setUserName(name);
		user.setUserAccount(account);
		
		String salt = Hash.getSalt();
		user.setUserSalt(salt);
		
		String passwordHash = Hash.getHash(password, salt);
		user.setUserPassword(passwordHash);
		
		user.setUserPhone(phone);
		user.setUserAddress(address);
		// 目前先預設，後面要新增員工
		user.setUserRole(role);
		
		userRepository.save(user);
	}
	
	@Transactional
	public void logoutUser(UserDto userDto) {
		if(userDto != null) {
			User user = userRepository.findByUserAccount(userDto.getUserAccount());
			user.setUserActive(false);
			userRepository.save(user);
		}
	}
	
}
