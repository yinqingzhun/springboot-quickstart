package com.yqz.springboot.quickstart.model.po;

import java.util.Date;

public class ChatMessage {

	private Integer id;

	private Integer senderId;

	private Integer receiverId;

	private Byte messageType;

	private String messageJson;

	private Date createTime;

	private Boolean isDel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public Byte getMessageType() {
		return messageType;
	}

	public void setMessageType(Byte messageType) {
		this.messageType = messageType;
	}

	public String getMessageJson() {
		return messageJson;
	}

	public void setMessageJson(String messageJson) {
		this.messageJson = messageJson == null ? null : messageJson.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}
}