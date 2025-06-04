package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.exception.PasswordErrorException;
import com.example.demo.exception.UserNoFindException;
import com.example.demo.model.dto.UserDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginRestController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Void>> login(@RequestParam String useraccount, @RequestParam String userpassword, HttpSession session) throws UserNoFindException, PasswordErrorException{
		
		UserDto userDto = userService.getUser(useraccount, userpassword);
		session.setAttribute("userDto", userDto);
		return ResponseEntity.ok(ApiResponse.success("登入成功", null));
		
	}

}
