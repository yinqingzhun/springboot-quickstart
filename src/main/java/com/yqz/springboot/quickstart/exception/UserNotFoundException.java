package com.yqz.springboot.quickstart.exception;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(int userId) {
		super("could not find user '" + userId + "'.");
	}
}