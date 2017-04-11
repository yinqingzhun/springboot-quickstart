package com.yqz.springboot.quickstart.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.databind.type.TypeFactory;

public class MyMessage {

	public MyMessage() {
	}

	public MyMessage(String msg) {
		this(msg, LocalDateTime.now());

	}

	public MyMessage(String msg, LocalDateTime dt) {
		this.message = msg;
		this.createTime = dt;
		TypeFactory.defaultInstance().constructParametrizedType(Set.class, Set.class, Integer.class);
	}

	@NotNull
	@Length(min = 1, max = 10)
	private String message;
	private LocalDateTime createTime;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

}
