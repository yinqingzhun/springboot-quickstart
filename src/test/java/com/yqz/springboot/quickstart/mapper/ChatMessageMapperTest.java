package com.yqz.springboot.quickstart.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.yqz.springboot.quickstart.Main;
import com.yqz.springboot.quickstart.model.po.ChatMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class ChatMessageMapperTest {
	@Autowired
	ChatMessageMapper mapper;

	// @Test
	// public void testGroup() {
	// List<KeyValuePair> map = mapper.getReceivedMessageCount();
	// Assert.notNull(map);
	// Assert.isTrue(map.size() > 0);
	// }

	@Test
	public void testSelectByPrimaryKey() {
		List<ChatMessage> o = mapper.selectAll();
		Assert.notNull(o);
		Assert.notEmpty(o);
	}
}
