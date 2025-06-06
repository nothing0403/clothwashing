package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.exception.PasswordErrorException;
import com.example.demo.exception.UserNoFindException;
import com.example.demo.model.dto.ClothDto;
import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.ClothService;
import com.example.demo.service.ItemService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/rest")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class LoginRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private ClothService clothService;
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/home")
	public ResponseEntity<ApiResponse<List<ClothDto>>> home(){
		
		List<ClothDto> clothesDto = clothService.getAllClothes();
		
		return ResponseEntity.ok(ApiResponse.success(null, clothesDto));
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Void>> login(@RequestParam String useraccount, @RequestParam String userpassword, HttpSession session) throws UserNoFindException, PasswordErrorException{
		
		UserDto userDto = userService.getUser(useraccount, userpassword);
		// 把使用者資訊存放進session
		session.setAttribute("userDto", userDto);
		
		return ResponseEntity.ok(ApiResponse.success("登入成功", null));
		
	}
	
	@PostMapping("/order")
	public ResponseEntity<ApiResponse<Void>> order(@RequestBody List<ClothDto> clothDtos){
		List<ClothDto> clothDtos2 = clothDtos.stream().filter(clothDto -> clothDto.getClothQuantity()!=0 ).collect(Collectors.toList());
		for(ClothDto clothDto: clothDtos2) {
			itemService.addItem(clothDto.getClothId(), clothDto.getClothQuantity());
		}
		return ResponseEntity.ok(ApiResponse.success("訂單已建立", null));
	}
	
	@PutMapping("/logout")
	public ResponseEntity<ApiResponse<Void>> logout(HttpSession session){
		
		UserDto userDto = (UserDto)session.getAttribute("userDto");
		
		userService.logoutUser(userDto);
		
		return ResponseEntity.ok(ApiResponse.success("登出成功", null));
	}
	

	@PostMapping("/submit")
	public ResponseEntity<ApiResponse<Void>> submit(@RequestParam String username, @RequestParam String useraccount, @RequestParam String userpassword, 
			@RequestParam String userphone, @RequestParam String useraddress, HttpSession session){
		
		userService.addUser(username, useraccount, userpassword, userphone, useraddress);
		
		return ResponseEntity.ok(ApiResponse.success("註冊成功", null));
	} 
}
