package com.yqz.springboot.quickstart.model.po;

public class ChatMessageMark {
    private Integer id;

    private Integer receiverId;

    private Integer senderId;

    private Integer pushedChatMessageId;

    private Integer maxChatMessageId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getPushedChatMessageId() {
        return pushedChatMessageId;
    }

    public void setPushedChatMessageId(Integer pushedChatMessageId) {
        this.pushedChatMessageId = pushedChatMessageId;
    }

    public Integer getMaxChatMessageId() {
        return maxChatMessageId;
    }

    public void setMaxChatMessageId(Integer maxChatMessageId) {
        this.maxChatMessageId = maxChatMessageId;
    }
}