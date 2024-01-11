package com.imdb.exception;

public class ReviewsDataNotPresentException extends RuntimeException {
	
	String message;

	public ReviewsDataNotPresentException(String message) {
		super(message);
		this.message = message;
	}
	
	

}
