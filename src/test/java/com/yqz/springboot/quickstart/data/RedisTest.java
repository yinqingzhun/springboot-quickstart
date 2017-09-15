package com.yqz.springboot.quickstart.data;

/*import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yqz.springboot.quickstart.Application;
import com.yqz.springboot.quickstart.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, Person> redisTemplate;

	*//*
	 * @Test public void test() { fail("Not yet implemented"); }
	 *//*

	@Test
	public void set() throws Exception {

		// 淇濆瓨瀛楃涓�
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

		BoundHashOperations<String, String, Integer> ho = redisTemplate.boundHashOps("weather");
		ho.put("鍖椾含", 1);
		ho.put("涓婃捣", 2);
		Assert.assertTrue(redisTemplate.boundHashOps("weather").size() == 2);
	}

	@Test
	public void setObject() throws Exception {
		BoundValueOperations<String, Person> op = redisTemplate.boundValueOps("person");
		Person p = new Person();
		p.setId(100);
		p.setName("jack");
		op.set(p);

		op = redisTemplate.boundValueOps("person");
		Assert.assertNotNull(op.get());
		Assert.assertEquals(op.get().getId(), 100);
	}

}*/
