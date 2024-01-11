package com.imdb.exception;

public class MoviDataNotPresentException extends RuntimeException {
	
	String message;

	public MoviDataNotPresentException(String message) {
		super(message);
		this.message = message;
	}
	

}
