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

@Service
// 透過 repository 抓資料
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MapToDto mapToDto;
	
	@Override
	public UserDto getUser(String useraccount, String userpassword) throws UserNoFindException, PasswordErrorException{
		// useraccount 是唯一值
		User user = userRepository.findByUserAccount(useraccount);
		if(user == null) {
			throw new UserNoFindException("查無此人");
		}
		
		String passwordHash = Hash.getHash(userpassword, user.getUserSalt());
		if(passwordHash.equals(user.getUserPassword())) {
			throw new PasswordErrorException("密碼錯誤");
		}
		// 將 user 轉成 userDTO 並回傳
		return mapToDto.userToDto(user);
	}

	@Override
	public void addUser(String name, String email, String password, String phone, String address) {
		
		User user = new User();
		
		user.setUserName(name);
		user.setUserEmail(email);
		
		String salt = Hash.getSalt();
		String passwordHash = Hash.getHash(password, salt);
		user.setUserPassword(passwordHash);
		
		user.setUserPhone(phone);
		user.setUserAddress(address);
		// 目前先預設，後面要新增員工
		user.setUserRole("customer");
		
		userRepository.save(user);
	}
	
	
}
