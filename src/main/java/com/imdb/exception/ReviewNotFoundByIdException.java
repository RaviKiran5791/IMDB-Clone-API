package com.imdb.exception;

public class ReviewNotFoundByIdException extends RuntimeException {
	
	String message;

	public ReviewNotFoundByIdException(String message) {
		super(message);
		this.message = message;
	}
	
	

}
