package com.yqz.springboot.quickstart.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import com.yqz.springboot.quickstart.model.KeyValuePair;
import com.yqz.springboot.quickstart.model.po.ChatMessage;

@Mapper
public interface ChatMessageMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ChatMessage record);

	int insertSelective(ChatMessage record);

	ChatMessage selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ChatMessage record);

	int updateByPrimaryKey(ChatMessage record);

	@MapKey("key")
	Map<Integer, KeyValuePair<Integer, Long>> getReceivedMessageCount();

	List<ChatMessage> selectAll();

	List<Integer> getAllIdList();
}