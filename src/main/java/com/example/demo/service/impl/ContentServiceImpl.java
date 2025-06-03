package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.entity.Content;
import com.example.demo.repository.ContentRepository;
import com.example.demo.service.ContentService;

public class ContentServiceImpl implements ContentService{

	@Autowired
    private ContentRepository contentRepository;

	@Override
	public void addContent() {
		Content content = new Content();
		
		
	}

}
