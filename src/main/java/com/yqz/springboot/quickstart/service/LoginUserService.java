package com.yqz.springboot.quickstart.service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// if ("u".equalsIgnoreCase(username))
		// return USER;

		// 因为UserEntity实现了UserDetails，所以也可以直接返回user
		User user = new User(username, "p", true, true, true, true,
				Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));

		return user;
	}

}
