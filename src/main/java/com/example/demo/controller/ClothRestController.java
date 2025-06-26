package com.example.demo.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.PasswordErrorException;
import com.example.demo.exception.UserNoFindException;
import com.example.demo.model.dto.CartDto;
import com.example.demo.model.dto.ClothDto;
import com.example.demo.model.dto.ContentDto;
import com.example.demo.model.dto.DeliverDto;
import com.example.demo.model.dto.ReceiverDto;
import com.example.demo.model.dto.SenderDto;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.Content;
import com.example.demo.model.entity.Receiver;
import com.example.demo.model.entity.User;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.ClothService;
import com.example.demo.service.ContentService;
import com.example.demo.service.EmailService;
import com.example.demo.service.ItemService;
import com.example.demo.service.ReceiverService;
import com.example.demo.service.SenderService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ClothRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ClothService clothService;
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private EmailService emailService;
	
	private String authcode;
	
	@GetMapping("/home")
	public ResponseEntity<ApiResponse<List<ClothDto>>> home(HttpSession session){
		
		List<ClothDto> clothDtos = session.getAttribute("clothDtos") == null? clothService.getCloth(): (List<ClothDto>)session.getAttribute("clothDtos");
	
		session.setAttribute("clothDtos", clothDtos);
		
		return ResponseEntity.ok(ApiResponse.success(null, clothDtos));
	}
	
	@GetMapping("/contentlist")
	public ResponseEntity<ApiResponse<UserDto>> findUserRole(HttpSession session){
		
		UserDto userDto = (UserDto)session.getAttribute("userDto");
		
		return ResponseEntity.ok(ApiResponse.success(null, userDto));
	}
	
	@GetMapping("/contentlist/receivedate_search")
	public ResponseEntity<ApiResponse<List<ContentDto>>> receivedate_search(@RequestParam String receiveDate, HttpSession session){
		System.out.println(receiveDate);
		
		List<ContentDto> contentDtos = contentService.getContentsByReceiveDate(receiveDate);
		return ResponseEntity.ok(ApiResponse.success(null, contentDtos));
	}
	
	@GetMapping("/contentlist/senddate_search")
	public ResponseEntity<ApiResponse<List<ContentDto>>> senddate_search(@RequestParam String sendDate, HttpSession session){
		System.out.println(sendDate);
		
		List<ContentDto> contentDtos = contentService.getContentsBySendDate(sendDate);
		return ResponseEntity.ok(ApiResponse.success(null, contentDtos));
	}
	
	@PostMapping("/contentlist/content_update")
	public ResponseEntity<ApiResponse<Void>> content_update(@RequestBody List<ContentDto> contentDtos, HttpSession session){
		
		contentService.updateContent(contentDtos);
		
		return ResponseEntity.ok(ApiResponse.success(null, null));
	}
	
	@PostMapping("/update")
	public ResponseEntity<ApiResponse<Void>> update(@RequestBody List<ClothDto> clothDtos, HttpSession session){
		
		session.setAttribute("clothDtos", clothDtos);
		
		List<ClothDto> filterClothDtos = clothDtos.stream().filter(clothDto -> clothDto.getClothQuantity()!=0).collect(Collectors.toList());
		
		session.setAttribute("filterClothDtos", filterClothDtos);
		
		return ResponseEntity.ok(ApiResponse.success(null, null));
	}
	
	@GetMapping("/deliver")
	public ResponseEntity<ApiResponse<DeliverDto>> deliver(HttpSession session){
		
		DeliverDto deliverDto = session.getAttribute("deliverDto") == null? new DeliverDto(): (DeliverDto)session.getAttribute("deliverDto");
		
		session.setAttribute("deliverDto", deliverDto);
		
		return ResponseEntity.ok(ApiResponse.success(null, deliverDto));
	}
	
	@PostMapping("/update/deliver")
	public ResponseEntity<ApiResponse<Void>> deliver(@RequestBody DeliverDto deliverDto, HttpSession session){
		
		session.setAttribute("deliverDto", deliverDto);
		
		return ResponseEntity.ok(ApiResponse.success(null, null));
	}	
	
	@GetMapping("/cart")
	public ResponseEntity<ApiResponse<CartDto>> cart(HttpSession session){
		
		List<ClothDto> clothDtos = (List<ClothDto>)session.getAttribute("filterClothDtos");
		
		DeliverDto deliverDto = (DeliverDto)session.getAttribute("deliverDto");
		
		ReceiverDto receiverDto = deliverDto.getReceiverDto();
		
		SenderDto senderDto = deliverDto.getSenderDto();
		
		CartDto cartDto = new CartDto();
		
		cartDto.setClothDtos(clothDtos);
		
		cartDto.setSenderDto(senderDto);
		
		cartDto.setReceiverDto(receiverDto);
		
		return ResponseEntity.ok(ApiResponse.success(null, cartDto));
	}
	
	@GetMapping("/result")
	public ResponseEntity<ApiResponse<Void>> result(HttpSession session){
		
		UserDto userDto = (UserDto)session.getAttribute("userDto");
		
        List<ClothDto> clothDtos = (List<ClothDto>)session.getAttribute("filterClothDtos");
		
		DeliverDto deliverDto = (DeliverDto)session.getAttribute("deliverDto");
		
		ReceiverDto receiverDto = deliverDto.getReceiverDto();
		
		SenderDto senderDto = deliverDto.getSenderDto();
		
		if(emailService.sendEmail(userDto.getUserAccount(), senderDto, receiverDto, clothDtos)) {
			
			contentService.addContent(userDto.getUserAccount(), senderDto, receiverDto, clothDtos);
			
			session.removeAttribute("clothDtos");
			
			session.removeAttribute("filterClothDtos");
			
			session.removeAttribute("deliverDto");
			
			return ResponseEntity.ok(ApiResponse.success(null, null));
		}
		else {
			return ResponseEntity.ok(ApiResponse.success("400", null));
		}
	}
	
	@GetMapping(value = "/captcha", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getCaptcha(HttpServletResponse response) throws IOException {
	    authcode = generateAuthCode(); // 產生驗證碼文字
	    BufferedImage image = getAuthCodeImage(authcode); // 產生圖片

	    response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0);
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);

	    ImageIO.write(image, "JPEG", response.getOutputStream());
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<UserDto>> login(@RequestParam String useraccount, @RequestParam String userpassword, @RequestParam String userauthcode, HttpSession session) throws UserNoFindException, PasswordErrorException{
		
		if(!(userauthcode.equals(authcode))) {
			return ResponseEntity.ok(ApiResponse.error(400, null));
		}
		
		UserDto userDto = userService.getUser(useraccount, userpassword);
		// 把使用者資訊存放進session
		session.setAttribute("userDto", userDto);
		
		session.removeAttribute("clothDtos");
		
		session.removeAttribute("filterClothDtos");
		
		session.removeAttribute("deliverDto");
		
		return ResponseEntity.ok(ApiResponse.success("登入成功", userDto));
	}
	
	@PostMapping("/login/forget")
	public ResponseEntity<ApiResponse<Void>> forget(@RequestParam String useraccount, @RequestParam String userpassword, HttpSession session) throws UserNoFindException, PasswordErrorException{
		
		if(userService.updateUser(useraccount, userpassword)) {
			return ResponseEntity.ok(ApiResponse.success("密碼重設成功", null));
		}
		else {
			return ResponseEntity.ok(ApiResponse.error(400, "密碼重設失敗"));
		}		
	}
	
	@GetMapping("/getUserInfo")
	public ResponseEntity<ApiResponse<String>> getUserInfo(HttpSession session) {
	    UserDto userDto = (UserDto)session.getAttribute("userDto");
	    if (userDto != null) {
	        return ResponseEntity.ok(ApiResponse.success("使用者已登入", userDto.getUserName()));
	    } else {
	        return ResponseEntity.ok(ApiResponse.error(400, "尚未登入"));
	    }
	}
	
	@GetMapping("/getUserClothInfo")
	public ResponseEntity<ApiResponse<List<ClothDto>>> getUserClothInfo(HttpSession session) {
	    
		List<ClothDto> clothDtos = (List<ClothDto>)session.getAttribute("filterClothDtos");

	    return ResponseEntity.ok(ApiResponse.success("使用者已登入", clothDtos));
	   
	}
	
	
	@PutMapping("/logout")
	public ResponseEntity<ApiResponse<Void>> logout(HttpSession session){
		
		UserDto userDto = (UserDto)session.getAttribute("userDto");
		
		userService.logoutUser(userDto);
		
        session.removeAttribute("clothDtos");
		
		session.removeAttribute("filterClothDtos");
		
		session.removeAttribute("deliverDto");
		
		session.removeAttribute("userDto");
		
		return ResponseEntity.ok(ApiResponse.success("登出成功", null));
	}
	

	@PostMapping("/submit")
	public ResponseEntity<ApiResponse<Void>> submit(@RequestParam String username, @RequestParam String useraccount, @RequestParam String userpassword, 
			@RequestParam String userphone, @RequestParam String useraddress, HttpSession session){
		
		String userrole = "customer";
		
		userService.addUser(username, useraccount, userpassword, userphone, useraddress, userrole);
		
		return ResponseEntity.ok(ApiResponse.success("註冊成功", null));
	} 
	
	@PostMapping("/employee/submit")
	public ResponseEntity<ApiResponse<Void>> employee_submit(@RequestParam String useraccount, @RequestParam String userpassword, @RequestParam String userrole){
		
		userService.addUser("", useraccount, userpassword, "", "", userrole);
		
		return ResponseEntity.ok(ApiResponse.success("註冊成功", null));
	}
	
	
	private String generateAuthCode() {
		String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer authcode = new StringBuffer();
		Random random = new Random();
		double number = Math.floor(Math.random()*2)+4;
		for(int i=0;i<number;i++) {
			int index = random.nextInt(chars.length()); // 隨機取位置
			authcode.append(chars.charAt(index)); // 取得該位置的資料
		}
		return authcode.toString();
	}
	
	private BufferedImage getAuthCodeImage(String authcode) {
		// 建立圖像區域(80x30 TGB)
		BufferedImage img = new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
		// 建立畫布
		Graphics g = img.getGraphics();
		
		Random random = new Random();
		// 設定顏色
		// 塗滿背景
		g.fillRect(0, 0, 100, 30); // 全區域
		// 設定顏色
		g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
		// 設定字型
		g.setFont(new Font("Segoe UI Emoji", Font.BOLD, 22)); // 字體, 風格, 大小
		// 繪文字
		g.drawString(authcode, 18, 22); // (18, 22) 表示繪文字左上角的起點
		// 加上干擾線
		g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
		
		for(int i=0;i<15;i++) {
			// 座標點
			int x1 = random.nextInt(100); // 0~79
			int y1 = random.nextInt(30); // 0~29
			int x2 = random.nextInt(100); // 0~79
			int y2 = random.nextInt(30); // 0~29
			// 繪直線
			g.drawLine(x1, y1, x2, y2);
		}
		return img;
		
	}
}
