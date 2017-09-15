package com.yqz.springboot.quickstart.config;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.yqz.springboot.quickstart.model.ReturnValue;
import com.yqz.springboot.quickstart.service.LoginUserService;
import org.springframework.stereotype.Component;

@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginUserService loginUserService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        // ShaPasswordEncoder shaPasswordEncoder =new ShaPasswordEncoder();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // shaPasswordEncoder.
        // Hash a password for the first time
        // String password="";
        // String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

        // gensalt's log_rounds parameter determines the complexity
        // the work factor is 2**log_rounds, and the default is 10
        // String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

        // Check that an unencrypted password matches one that has
        // previously been hashed
        // if (BCrypt.checkpw(candidate, hashed))
        // System.out.println("It matches");
        // else
        // System.out.println("It does not match");
        // authenticationProvider.setPasswordEncoder(new ShaPasswordEncoder());
        authenticationProvider.setUserDetailsService(loginUserService);
        return authenticationProvider;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return loginUserService;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return loginUserService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/img/**"); // #3
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/home", "/hi/**", "/error").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')").anyRequest()
                .authenticated().and().formLogin().loginPage("/login").permitAll()
//				.loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/home")
                //.successHandler(savedRequestAwareAuthenticationSuccessHandler())
                .and().logout()
                // .logoutUrl("/my/logout")// 2
                // .logoutSuccessUrl("/my/index") // 3
                // .logoutSuccessHandler(logoutSuccessHandler)//4
                .invalidateHttpSession(true) // 5
                // .addLogoutHandler(logoutHandler) // 6
                // .deleteCookies(cookieNamesToClear) //7
                .permitAll().and().rememberMe().key("sdkjfklasjdfdskljfdkslfjioweruk")
                .and().csrf().disable();

        // http.authorizeRequests().antMatchers("/", "/home")
        // .access("hasRole('USER') or hasRole('ADMIN') or
        // hasRole('DBA')").and().formLogin().loginPage("/login")
        // .usernameParameter("ssoId").passwordParameter("password").and().exceptionHandling()
        // .accessDeniedPage("/Access_Denied").authenticationEntryPoint(new
        // AuthenticationEntryPoint() {
        //
        // @Override
        // public void commence(HttpServletRequest request, HttpServletResponse
        // response,
        // AuthenticationException authException) throws IOException,
        // ServletException {
        //
        // String url = request.getRequestURI();
        // if (url.startsWith("/api")) {
        // response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        // } else {
        // response.sendRedirect("/users/login?redirectUrl=" + url);
        // }
        //
        // }
        // });
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler
    savedRequestAwareAuthenticationSuccessHandler() {

        SavedRequestAwareAuthenticationSuccessHandler auth
                = new SavedRequestAwareAuthenticationSuccessHandler();
        auth.setTargetUrlParameter("targetUrl");
        return auth;
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

    @Bean
    MyErrorController errorController(ErrorAttributes errorAttributes, ServerProperties serverProperties,
                                      ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider,
                                      List<ErrorViewResolver> errorViewResolvers) {

        MyErrorController myErrorController = new MyErrorController(errorAttributes, serverProperties.getError(),
                errorViewResolvers);

        return myErrorController;

    }


    public class MyErrorController extends BasicErrorController {

        public MyErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties,
                                 List<ErrorViewResolver> errorViewResolvers) {
            super(errorAttributes, errorProperties, errorViewResolvers);
        }

        @Override
        public ResponseEntity error(HttpServletRequest request) {
            Map<String, Object> errorAttributes = getErrorAttributes(request,
                    isIncludeStackTrace(request, MediaType.ALL));

            HttpStatus status = getStatus(request);
            ReturnValue body = ReturnValue.buildErrorResult(status.value(), errorAttributes.get("error").toString());
            return new ResponseEntity(body, status);
        }
    }

}
