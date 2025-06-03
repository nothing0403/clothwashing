package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.MapToDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.Hash;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MapToDto mapToDto;
	
	@Override
	public UserDto getUser(Integer id) {
		return mapToDto.userToDto(userRepository.findByUserId(id));
	}

	@Override
	public void addUser(String name, String account, String password, String phone, String address, String role) {
		String salt = Hash.getSalt();
		String passwordHash = Hash.getHash(password, salt);
		User user = new User();
		user.setUserName(name);
		user.setUserAccount(account);
		user.setUserPassword(passwordHash);
		user.setUserPhone(phone);
		user.setUserAddress(address);
		user.setUserRole(role);
		userRepository.save(user);
	}
}
