package com.yqz.springboot.quickstart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.yqz.springboot.quickstart.model.po.ChatMessage;

//@Secured({ "ROLE_USER", "ROLE_ADMIN" })

@Service
public class ChatMessageService {

	/*@Autowired
	ChatMessageMapper chatMessageMapper;

	// @PostAuthorize ("returnObject.type == authentication.name")
	public ChatMessage getById(int id) {
		return chatMessageMapper.selectByPrimaryKey(id);
	}

	// @PreAuthorize("hasRole('ADMIN')") @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
	public List<ChatMessage> getAll() {
		return chatMessageMapper.selectAll();
	}

	public List<Integer> getAllIdList() {
		return chatMessageMapper.getAllIdList();
	}

	public Object getReceivedMessageCount() {
		return chatMessageMapper.getReceivedMessageCount().entrySet().stream()
				.collect(Collectors.toMap(a -> a.getKey(), a -> a.getValue().getValue()));
	}*/
}
