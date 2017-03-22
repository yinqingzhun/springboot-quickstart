package com.yqz.springboot.quickstart.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yqz.springboot.quickstart.model.po.ChatMessage;

@Mapper
public interface ChatMessageMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ChatMessage record);

	int insertSelective(ChatMessage record);

	ChatMessage selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ChatMessage record);

	int updateByPrimaryKey(ChatMessage record);
}