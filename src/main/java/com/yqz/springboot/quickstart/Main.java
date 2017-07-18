package com.yqz.springboot.quickstart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.yqz.springboot.quickstart.interceptor.CaseInsensitiveRequestParameterNameFilter;

@SpringBootApplication
public class Main extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Main.class);
	}

	public static void main(String[] args) throws Exception {
		// SpringApplication.run(Main.class, args);
		new SpringApplicationBuilder().sources(Main.class).properties("spring.config.name=application")
				.bannerMode(Mode.OFF).run(args);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);

		/*
		 * FilterRegistration.Dynamic filter =
		 * servletContext.addFilter(CaseInsensitiveRequestParameterNameFilter.
		 * class.getSimpleName(), new
		 * CaseInsensitiveRequestParameterNameFilter());
		 * filter.addMappingForUrlPatterns(null, true, "/*");
		 */

	}

	@Bean
	public CaseInsensitiveRequestParameterNameFilter caseInsensitiveRequestParameterNameFilter() {
		return new CaseInsensitiveRequestParameterNameFilter();
	}

	@Bean
	public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
		ShallowEtagHeaderFilter filter = new ShallowEtagHeaderFilter();
		return filter;
	}

	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		// filter.setForceEncoding(true);
		return filter;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CaseInsensitiveRequestParameterNameFilter filter = new CaseInsensitiveRequestParameterNameFilter();
		registrationBean.setFilter(filter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

}
