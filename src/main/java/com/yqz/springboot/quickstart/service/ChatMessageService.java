package com.yqz.springboot.quickstart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yqz.springboot.quickstart.mapper.ChatMessageMapper;
import com.yqz.springboot.quickstart.model.po.ChatMessage;

@Service
public class ChatMessageService {

	@Autowired
	ChatMessageMapper chatMessageMapper;
	
	public ChatMessage getById(int id){
		return chatMessageMapper.selectByPrimaryKey(id);
	}
}
