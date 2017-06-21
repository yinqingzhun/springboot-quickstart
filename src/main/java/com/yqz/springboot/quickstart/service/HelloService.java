package com.yqz.springboot.quickstart.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.yqz.springboot.quickstart.component.App;

@PropertySource("classpath:/config/app.properties")
@Service
public class HelloService {

	@Autowired
	App app;

	@Value("${app.name}")
	String appName;

	public String getAppInfo() {
		return this.app.getDescription();
	}

	public String getAppName() {
		return appName;
	}

	@Cacheable(value = "datetime1")
	public String hello(String who) {
		return String.format("\nhello %-10s! ", who) + LocalDateTime.now().toString();
	}

	@Cacheable(value = "datetime2")
	public String welcome(String who) {
		return String.format("\nwelcome %-10s! ", who) + LocalDateTime.now().toString();
	}

}
