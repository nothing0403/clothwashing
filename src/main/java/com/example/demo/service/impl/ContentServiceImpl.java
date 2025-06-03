package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Content;
import com.example.demo.model.entity.Receiver;
import com.example.demo.model.entity.Sender;
import com.example.demo.model.entity.User;
import com.example.demo.repository.ContentRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private ItemServiceImpl itemServiceImpl;
	
	@Override
	public void addContent(User user, Sender sender, Receiver receiver, List<Item> items) {
		Content content = new Content();	
		// 將 user 、 sender 、 receiver 、 item 的物件存放進 content，JPA 會自動生成對應外鍵
		content.setUser(user);
		content.setSender(sender);
		content.setReceiver(receiver);
		content.setItems(items);
		
		// 訂單建立時間
		LocalDateTime dateTime = LocalDateTime.now();
		String dateTimeStr = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		content.setContentBuildTime(dateTimeStr);
		
		content.setContentState(false);
		
		content.setContentPrice(null);
		
		content.setContentPrice(itemServiceImpl.getItemPrice());
	}

}
