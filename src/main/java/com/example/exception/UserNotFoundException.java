package com.example.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(final Long id) {
			super("Could not find user with id " + id);
		}
}
