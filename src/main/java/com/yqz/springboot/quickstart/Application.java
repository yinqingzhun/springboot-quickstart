package com.yqz.springboot.quickstart;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.yqz.springboot.quickstart.interceptor.CaseInsensitiveRequestParameterNameFilter;

@SpringBootApplication
@EnableScheduling
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) throws Exception {
		// SpringApplication.run(Main.class, args);
		new SpringApplicationBuilder().sources(Application.class).properties("spring.config.name=application")
				.bannerMode(Mode.OFF).run(args);
	}

	@Scheduled(fixedDelay = 50000000)
	public void printTime() {
		System.out.println("It's is " + LocalDateTime.now());
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
		filter.setForceEncoding(true);
		return filter;
	}

	/*
	 * @Bean public FilterRegistrationBean filterRegistrationBean() {
	 * FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	 * CaseInsensitiveRequestParameterNameFilter filter = new
	 * CaseInsensitiveRequestParameterNameFilter();
	 * registrationBean.setFilter(filter); List<String> urlPatterns = new
	 * ArrayList<String>(); urlPatterns.add("/*");
	 * registrationBean.setUrlPatterns(urlPatterns); return registrationBean; }
	 */

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			/*
			 * System.out.
			 * println("Let's inspect the beans provided by Spring Boot:");
			 * 
			 * String[] beanNames = ctx.getBeanDefinitionNames();
			 * Arrays.sort(beanNames); for (String beanName : beanNames) {
			 * System.out.println(beanName); }
			 */

		};
	}

}
