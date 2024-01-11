package com.imdb.exception;

public class UserDataNotPresentException extends RuntimeException {
	private String message;

	public UserDataNotPresentException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
