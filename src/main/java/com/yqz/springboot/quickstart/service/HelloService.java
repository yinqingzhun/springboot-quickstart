package com.yqz.springboot.quickstart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.yqz.springboot.quickstart.component.App;

@Service
public class HelloService {

	@Autowired
	App app;

	public String getAppInfo() {
		return this.app.getDescription();
	}

}
