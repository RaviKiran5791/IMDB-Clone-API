package com.imdb.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.imdb.util.ErrorStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private ErrorStructure<String> errstructure;
	
	@ExceptionHandler(UserNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> userNotFoundById(UserNotFoundByIdException exception)
	{
		errstructure.setStatus(HttpStatus.NOT_FOUND.value());
		errstructure.setMessage(exception.getMessage());
		errstructure.setRootCause("User record not found for the requested ID !!");
		
		return new ResponseEntity<ErrorStructure>(errstructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFoundByEmailException.class)
	public ResponseEntity<ErrorStructure> userNotFoundByEmail(UserNotFoundByEmailException exception)
	{
		errstructure.setStatus(HttpStatus.NOT_FOUND.value());
		errstructure.setMessage(exception.getMessage());
		errstructure.setRootCause("User record not found for the requested Email !!");
		
		return new ResponseEntity<ErrorStructure>(errstructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserDataNotPresentException.class)
	public ResponseEntity<ErrorStructure> userDataNotFound(UserDataNotPresentException exception)
	{
		errstructure.setStatus(HttpStatus.NOT_FOUND.value());
		errstructure.setMessage(exception.getMessage());
		errstructure.setRootCause("Users record not Present!!");
		
		return new ResponseEntity<ErrorStructure>(errstructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MovieNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> movieNotFoundById(MovieNotFoundByIdException exception)
	{
		errstructure.setStatus(HttpStatus.NOT_FOUND.value());
		errstructure.setMessage(exception.getMessage());
		errstructure.setRootCause("Movie record not found for the requested ID !!");
		
		return new ResponseEntity<ErrorStructure>(errstructure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MovieNotFoundByMovieNameException.class)
	public ResponseEntity<ErrorStructure> movieNotFoundByMovieName(MovieNotFoundByMovieNameException exception)
	{
		errstructure.setStatus(HttpStatus.NOT_FOUND.value());
		errstructure.setMessage(exception.getMessage());
		errstructure.setRootCause("Movie record not found for the requested Movie Name !!");
		
		return new ResponseEntity<ErrorStructure>(errstructure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MoviDataNotPresentException.class)
	public ResponseEntity<ErrorStructure> movieDataNotFound(MoviDataNotPresentException exception)
	{
		errstructure.setStatus(HttpStatus.NOT_FOUND.value());
		errstructure.setMessage(exception.getMessage());
		errstructure.setRootCause("Movies record not Present !!");
		
		return new ResponseEntity<ErrorStructure>(errstructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ReviewNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure> reviewNotFoundById(ReviewNotFoundByIdException exception)
	{
		errstructure.setStatus(HttpStatus.NOT_FOUND.value());
		errstructure.setMessage(exception.getMessage());
		errstructure.setRootCause("Review record not found for the requested ID !!");
		
		return new ResponseEntity<ErrorStructure>(errstructure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ReviewsDataNotPresentException.class)
	public ResponseEntity<ErrorStructure> reviewsDataNotPresent(ReviewsDataNotPresentException exception)
	{
		errstructure.setStatus(HttpStatus.NOT_FOUND.value());
		errstructure.setMessage(exception.getMessage());
		errstructure.setRootCause("Reviews record not found to fetch !!");
		
		return new ResponseEntity<ErrorStructure>(errstructure,HttpStatus.NOT_FOUND);
	}
	

	
	

	
}
