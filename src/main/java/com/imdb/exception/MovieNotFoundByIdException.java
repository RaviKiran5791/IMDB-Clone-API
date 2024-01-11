package com.imdb.exception;

public class MovieNotFoundByIdException extends RuntimeException {
	
	String message;

	public MovieNotFoundByIdException(String message) {
		super(message);
		this.message = message;
	}
	

}
