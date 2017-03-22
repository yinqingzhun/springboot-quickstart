package com.yqz.springboot.quickstart.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
