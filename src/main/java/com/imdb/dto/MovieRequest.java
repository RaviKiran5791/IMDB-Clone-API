package com.imdb.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.imdb.entity.Review;
import com.imdb.enums.Genres;
import com.imdb.enums.Language;

public class MovieRequest {
	
	private String movieName;
	private Genres movieGenres;
	private Language movieLanguage;
	private LocalTime movieDuration;
	private List<Review> movieReviewList=new ArrayList<>();
	
	
	public List<Review> getMovieReviewList() {
		return movieReviewList;
	}
	public void setMovieReviewList(List<Review> movieReviewList) {
		this.movieReviewList = movieReviewList;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public Genres getMovieGenres() {
		return movieGenres;
	}
	public void setMovieGenres(Genres movieGenres) {
		this.movieGenres = movieGenres;
	}
	public Language getMovieLanguage() {
		return movieLanguage;
	}
	public void setMovieLanguage(Language movieLanguage) {
		this.movieLanguage = movieLanguage;
	}
	public LocalTime getMovieDuration() {
		return movieDuration;
	}
	public void setMovieDuration(LocalTime movieDuration) {
		this.movieDuration = movieDuration;
	}
	
	

}
