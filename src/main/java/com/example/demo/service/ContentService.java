package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.ContentDto;
import com.example.demo.model.entity.Item;
import com.example.demo.model.entity.Receiver;
import com.example.demo.model.entity.Sender;
import com.example.demo.model.entity.User;

public interface ContentService {

	public void addContent(User user, Sender sender, Receiver receiver, 
			List<Item> items, String receiveTime, String sendTime);
	
	public List<ContentDto> getContents(String useraccount);
}
