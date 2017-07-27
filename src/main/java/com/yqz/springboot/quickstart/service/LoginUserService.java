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

	private static UserDetails USER = null;

	static {
		USER = new UserDetails() {

			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isAccountNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public String getUsername() {
				// TODO Auto-generated method stub
				return "u";
			}

			@Override
			public String getPassword() {
				// TODO Auto-generated method stub
				return "p";
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				// TODO Auto-generated method stub
				return Arrays.asList(new GrantedAuthority() {

					@Override
					public String getAuthority() {
						return "user";
					}

				});
			}
		};

	}

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
