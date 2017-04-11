package com.yqz.springboot.quickstart.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class SpringCacheConfig extends CachingConfigurerSupport {

	@Bean
	@Override
	public CacheManager cacheManager() {
		// configure and return an implementation of Spring's CacheManager SPI
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
		return cacheManager;

	}

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		// configure and return an implementation of Spring's KeyGenerator SPI
		return new SimpleKeyGenerator();
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setUsePool(true);
		factory.setHostName("localhost");
		factory.setPort(6379);
		return factory;
	}

	@Bean
	<T> RedisTemplate<String, T> redisTemplate() {
		RedisTemplate<String, T> template = new RedisTemplate<String, T>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setStringSerializer(new StringRedisSerializer());
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setEnableDefaultSerializer(true);
		template.afterPropertiesSet();
		return template;
	}

}
