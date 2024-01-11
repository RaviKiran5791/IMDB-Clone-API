package com.imdb.exception;

public class UserNotFoundByEmailException extends RuntimeException {
	
	private String message;

	public UserNotFoundByEmailException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage()
	{
		return message;
	}

}
