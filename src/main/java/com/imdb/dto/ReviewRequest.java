package com.imdb.dto;

import com.imdb.entity.Movie;
import com.imdb.entity.User;

public class ReviewRequest {
	
	private String reviewMessage;
	private Double reviewRating;
	private User user;
	private Movie movie;
	
	public String getReviewMessage() {
		return reviewMessage;
	}
	public void setReviewMessage(String reviewMessage) {
		this.reviewMessage = reviewMessage;
	}
	public Double getReviewRating() {
		return reviewRating;
	}
	public void setReviewRating(Double reviewRating) {
		this.reviewRating = reviewRating;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	

}
