package com.yqz.springboot.quickstart.model;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.databind.type.TypeFactory;

public class MyMessage {

	public MyMessage() {
	}

	public MyMessage(String msg) {
		this(msg, "");

	}

	public MyMessage(String msg, String dt) {
		this.message = msg;
		this.createTime = dt;
		TypeFactory.defaultInstance().constructParametrizedType(Set.class, Set.class, Integer.class);
	}

	@NotNull
	@Length(min = 1, max = 10)
	private String message;
	private String createTime;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
