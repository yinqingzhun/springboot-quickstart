package com.yqz.springboot.quickstart.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.yqz.springboot.quickstart.handler.RestHandlerExceptionResolver;

@Configuration
@PropertySource("config/app.properties")
public class WebMvcConfig extends WebMvcConfigurationSupport implements ApplicationContextAware {

	/*
	 * @Bean public ValidationInterceptor validationInterceptor() {
	 * RequestMappingHandlerAdapter requestMappingHandlerAdapter =
	 * getApplicationContext() .getBean(RequestMappingHandlerAdapter.class);
	 * ValidationInterceptor v = new
	 * ValidationInterceptor(requestMappingHandlerAdapter); return v; }
	 */

	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
		AntPathMatcher pathMatcher = new AntPathMatcher();
		pathMatcher.setCaseSensitive(false);
		mapping.setPathMatcher(pathMatcher);
		// mapping.setInterceptors(getApplicationContext().getBean(ValidationInterceptor.class));
		return mapping;
	}

	@Override
	protected void configurePathMatch(PathMatchConfigurer configurer) {
		AntPathMatcher pathMatcher = new AntPathMatcher();
		pathMatcher.setCaseSensitive(false);
		configurer.setPathMatcher(pathMatcher);
		super.configurePathMatch(configurer);
	}

	@Override
	protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(new ExceptionHandlerExceptionResolver());
		exceptionResolvers.add(new RestHandlerExceptionResolver());
		exceptionResolvers.add(new DefaultHandlerExceptionResolver());
		exceptionResolvers.add(new SimpleMappingExceptionResolver());
	}

}
