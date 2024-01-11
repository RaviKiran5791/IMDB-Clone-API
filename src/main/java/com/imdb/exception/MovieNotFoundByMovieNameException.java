package com.imdb.exception;

public class MovieNotFoundByMovieNameException extends RuntimeException {
	
	String message;

	public MovieNotFoundByMovieNameException(String message) {
		super(message);
		this.message = message;
	}
	

}
