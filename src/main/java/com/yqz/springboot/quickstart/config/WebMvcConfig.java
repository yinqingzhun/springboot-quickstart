package com.yqz.springboot.quickstart.config;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.yqz.springboot.quickstart.handler.RestHandlerExceptionResolver;

@Configuration
@PropertySource("classpath:/config/app.properties")
public class WebMvcConfig extends WebMvcConfigurationSupport {

	/*
	 * @Bean public RequestMappingHandlerMapping requestMappingHandlerMapping()
	 * { RequestMappingHandlerMapping mapping = new
	 * RequestMappingHandlerMapping(); AntPathMatcher pathMatcher = new
	 * AntPathMatcher(); pathMatcher.setCaseSensitive(false);
	 * mapping.setPathMatcher(pathMatcher); //
	 * mapping.setInterceptors(getApplicationContext().getBean(
	 * ValidationInterceptor.class)); return mapping; }
	 */

	@Override
	protected PathMatchConfigurer getPathMatchConfigurer() {
		PathMatchConfigurer configurer = new PathMatchConfigurer();
		AntPathMatcher pathMatcher = new AntPathMatcher();
		pathMatcher.setCaseSensitive(false);
		configurer.setPathMatcher(pathMatcher);
		return configurer;
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
		exceptionResolvers.add(new DefaultHandlerExceptionResolver());
		exceptionResolvers.add(new SimpleMappingExceptionResolver());
		exceptionResolvers.add(new RestHandlerExceptionResolver());
	}

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		resolver.setOrder(Ordered.LOWEST_PRECEDENCE);
		return resolver;
	}

	@Bean
	public ResourceBundleViewResolver resourceBundleViewResolver() {
		ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
		resolver.setBasename("views.bundle");// classpath location. note that
												// the JDK's standard
												// ResourceBundle treats dots as
												// package separators
		resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return resolver;
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		WebContentInterceptor webContentInterceptor = new WebContentInterceptor();
		webContentInterceptor.setCacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS));
		webContentInterceptor.setRequireSession(false);
		webContentInterceptor.setSupportedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
				HttpMethod.DELETE.name());
		registry.addInterceptor(webContentInterceptor);

		// OpenSessionInViewInterceptor openSessionInViewInterceptor = new
		// OpenSessionInViewInterceptor();
		// openSessionInViewInterceptor.setSessionFactory(getApplicationContext().getBean(SqlSessionFactory.class));
		// registry.addWebRequestInterceptor(openSessionInViewInterceptor);
	}

	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
		objectMapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		// objectMapper.configOverride(Date.class).setFormat(JsonFormat.Value.forPattern("yyyy-MM-dd"));

		JavaTimeModule javaTimeModule = new JavaTimeModule();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
		javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
		javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
		objectMapper.registerModule(javaTimeModule);

		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		converters.add(new MappingJackson2HttpMessageConverter(objectMapper));

		// JacksonXmlModule module = new JacksonXmlModule();
		// module.setDefaultUseWrapper(false);
		// XmlMapper xmlMapper = new XmlMapper(module);
		// xmlMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		// converters.add(new
		// MappingJackson2XmlHttpMessageConverter(xmlMapper));
	}

	@Override
	protected void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new Converter<String, Long>() {

			@Override
			public Long convert(String source) {
				if (StringUtils.hasText(source))
					Long.parseLong(source);
				return null;
			}

		});

	}

	 @Bean
	 @Primary
	 public ITemplateResolver templateResolver() {
	 SpringResourceTemplateResolver templateResolver = new
	 SpringResourceTemplateResolver();
	 templateResolver.setPrefix("classpath:/templates/");
	 templateResolver.setSuffix(".html");
	 templateResolver.setTemplateMode("HTML5");
	 templateResolver.setCharacterEncoding("utf-8");
	 templateResolver.setCacheable(false);
	 return templateResolver;
	 }

	 @Bean
	 @Primary
	 public ViewResolver springThymeleafViewResolver() {
	 ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	 viewResolver.setTemplateEngine(getApplicationContext().getBean(SpringTemplateEngine.class));
	 viewResolver.setOrder(1);
	 viewResolver.setCharacterEncoding("UTF-8");
	 return viewResolver;
	 }
	 

}
