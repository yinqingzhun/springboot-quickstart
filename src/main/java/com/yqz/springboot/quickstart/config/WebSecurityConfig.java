package com.yqz.springboot.quickstart.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.yqz.springboot.quickstart.service.LoginUserService;

@EnableGlobalMethodSecurity(prePostEnabled=true,jsr250Enabled=true,securedEnabled=true)  
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	LoginUserService loginUserService;

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		
//		ShaPasswordEncoder shaPasswordEncoder =new ShaPasswordEncoder();
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		
		//shaPasswordEncoder.
		// Hash a password for the first time
//		String password=""; 
//		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

		// gensalt's log_rounds parameter determines the complexity
		// the work factor is 2**log_rounds, and the default is 10
//		String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

		// Check that an unencrypted password matches one that has
		// previously been hashed
//		if (BCrypt.checkpw(candidate, hashed))
//			System.out.println("It matches");
//		else
//			System.out.println("It does not match");
//		 authenticationProvider.setPasswordEncoder(new ShaPasswordEncoder());
		authenticationProvider.setUserDetailsService(loginUserService);
		return authenticationProvider;
	}

	/*
	 * @Override protected UserDetailsService userDetailsService() { return
	 * loginUserService; }
	 * 
	 * @Bean
	 * 
	 * @Override public UserDetailsService userDetailsServiceBean() throws
	 * Exception { return super.userDetailsServiceBean(); }
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/img/**"); // #3
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/home").permitAll().antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')").anyRequest().authenticated().and()
				.formLogin().loginPage("/login").permitAll().and().logout()
				// .logoutUrl("/my/logout")// 2
				// .logoutSuccessUrl("/my/index") // 3
				// .logoutSuccessHandler(logoutSuccessHandler)//4
				.invalidateHttpSession(true) // 5
				// .addLogoutHandler(logoutHandler) // 6
				// .deleteCookies(cookieNamesToClear) //7
				.permitAll();
		
//		 http.authorizeRequests()
//	        .antMatchers("/", "/home").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
//	        .and().formLogin().loginPage("/login")
//	        .usernameParameter("ssoId").passwordParameter("password")
//	        .and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}

	// @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder auth) throws
	// Exception {
	// //
	// auth.inMemoryAuthentication().withUser("user").password("pwd").roles("USER");
	// //
	// auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema().withUser("u").password("u").roles("USER")
	// // .and().withUser("a").password("a").roles("USER", "ADMIN");
	// auth.jdbcAuthentication().dataSource(dataSource)
	// .usersByUsernameQuery("select username,password, enabled from users where
	// username=?")
	// .authoritiesByUsernameQuery("select username, authority from authorities
	// where username=?");
	// }

}
