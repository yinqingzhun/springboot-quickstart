package com.yqz.springboot.quickstart.controller;

import java.util.HashMap;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yqz.springboot.quickstart.model.po.ChatMessage;
import com.yqz.springboot.quickstart.service.ChatMessageService;

@Validated
@RestController
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	ChatMessageService chatMessageService;

	// @Valid
	@GetMapping("/{id}")
	public ChatMessage getById(@PathVariable("id") @Min(value = 1, message = "id必须为正整数") int id) {
		return chatMessageService.getById(id);
	}

	
}
