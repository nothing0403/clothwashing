package com.example.demo.service.impl;

import com.example.demo.model.dto.ClothDto;
import com.example.demo.model.dto.ReceiverDto;
import com.example.demo.model.dto.SenderDto;
import com.example.demo.service.EmailService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
		
	// Google應用程式密碼
	// 請參考此篇 https://www.yongxin-design.com/Article/10
	// 請自行產生Google應用程式密碼
	
	// 已產生
	String googleAppPassword = "ylah vukc tumd kxzf";

	// 寄件者的電子郵件地址
	String from = "ladeplatoffice@gmail.com";
	
	// 使用 Gmail SMTP 伺服器
	String host = "smtp.gmail.com";
	
	// to: // 收件者的電子郵件地址
	public boolean sendEmail(String userAccount, SenderDto senderDto, ReceiverDto receiverDto, List<ClothDto>clothDtos) {

		StringBuilder clothDetailBuilder = new StringBuilder();
		
		for(ClothDto clothDto : clothDtos) {
			clothDetailBuilder.append(String.format(
				"名稱: %s\n描述: %s\n價格: %d\t數量: %d\n\n",
				clothDto.getClothName(),
				clothDto.getClothDescription(),
				clothDto.getClothPrice(),
				clothDto.getClothQuantity()
			));
		}
		
		String clothDetail = clothDetailBuilder.toString();
		
		LocalDateTime dateTime = LocalDateTime.now();
		String dateTimeStr = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String[] dateTimeSpilt = dateTimeStr.split(" ");
		String[] dateDetail = dateTimeSpilt[0].split("-");
		
		
		String emailText = String.format( """
				
				您好，以下是本次的訂單資料，洗衣外送平台感謝您的光臨。
				
				衣物訂單
				
				日期: %s年%s月%s日
				時間: %s
				
				%s
				總金額: %d 元
				
				寄件人資料
				
				姓名: %s	
				電話: %s
				地址: %s	
				收件日期: %s	
				收件時段: %s	
				付款方式: %s
				
				收件人資料
				
				姓名: %s	
				電話: %s	
				地址: %s	
				到件日期: %s
				
				""", 
				dateDetail[0],
				dateDetail[1],
				dateDetail[2],
				dateTimeSpilt[1],
				clothDetail, 
				clothDtos.stream().mapToInt(clothDto -> clothDto.getClothPrice()*clothDto.getClothQuantity()).sum(),
				senderDto.getSenderName(),
				senderDto.getSenderPhone(),
				senderDto.getSenderAddress(),
				senderDto.getSenderDate(),
				senderDto.getSenderTimePeriod(),
				senderDto.getSenderPayment(),
				receiverDto.getReceiverName(),
				receiverDto.getReceiverPhone(),
				receiverDto.getReceiverAddress(),
				receiverDto.getReceiverDate());

		// 設定屬性
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");

		// 建立會話物件，並提供驗證資訊
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				// Google應用程式密碼請參考此篇
				// https://www.yongxin-design.com/Article/10
				return new PasswordAuthentication(from, googleAppPassword);
			}
		});

		try {
			// 建立一個預設的 MimeMessage 物件
			Message message = new MimeMessage(session);

			// 設定寄件者
			message.setFrom(new InternetAddress(from));

			// 設定收件者
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userAccount));

			// 設定郵件標題
			message.setSubject("Laundry Delivery Platform 訂單明細");

			// 設定郵件內容讓使用者點選連結（confirmUrl）進行確認
			message.setText(emailText );

			// 傳送郵件
			Transport.send(message);

			// 發送成功 Log
			System.out.println("發送成功: " + userAccount);
			
			return true;

		} catch (MessagingException e) {
			// 發送失敗 Log
			System.out.println("發送失敗: " + e.getMessage());
			
			return false;
		}
	}

	@Override
	public String verifyEmail(String userAccount) {

	    // 產生驗證用 token（可存在資料庫或暫存）
	    String token = UUID.randomUUID().toString();

	    // 設計你的驗證連結
	    String verifyUrl = "http://localhost:8081/rest/submit/verify?token=" + token;

	    // Email 內容（HTML 版本）
	    String emailContent = String.format("""
	        <p>您好，</p>
	        <p>感謝您註冊本平台，請點擊以下連結以完成信箱驗證：</p>
	        <p><a href="%s">%s</a></p>
	        <p>如您未申請驗證，請忽略此封信件。</p>
	        """, verifyUrl, verifyUrl);

	    // 設定 mail 屬性
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", "587");

	    // 建立 session
	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(from, googleAppPassword);
	        }
	    });

	    try {
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userAccount));
	        message.setSubject("帳號驗證信 - Laundry Delivery Platform");
	        message.setContent(emailContent, "text/html; charset=utf-8"); // 使用 HTML 格式

	        Transport.send(message);

	        // ✅ 實際應用中：儲存 token 與信箱的對應關係到資料庫以供驗證
	        // e.g. emailTokenRepository.save(new EmailToken(userAccount, token));

	        System.out.println("驗證信寄送成功：" + userAccount);
	        return token;

	    } catch (MessagingException e) {
	        System.out.println("驗證信寄送失敗：" + e.getMessage());
	        return null;
	    }
	}
}
