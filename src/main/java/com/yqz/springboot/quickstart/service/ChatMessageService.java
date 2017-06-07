package com.yqz.springboot.quickstart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yqz.springboot.quickstart.mapper.ChatMessageMapper;
import com.yqz.springboot.quickstart.model.po.ChatMessage;

@Service
public class ChatMessageService {

	@Autowired
	ChatMessageMapper chatMessageMapper;

	public ChatMessage getById(int id) {
		return chatMessageMapper.selectByPrimaryKey(id);
	}

	public List<ChatMessage> getAll() {
		return chatMessageMapper.selectAll();
	}

	public List<Integer> getAllIdList() {
		return chatMessageMapper.getAllIdList();
	}

	public Object getReceivedMessageCount() {
		return chatMessageMapper.getReceivedMessageCount().entrySet().stream()
				.collect(Collectors.toMap(a -> a.getKey(), a -> a.getValue().getValue()));
	}
}
