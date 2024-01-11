package com.imdb.dto;

import java.time.LocalTime;

import com.imdb.enums.Genres;
import com.imdb.enums.Language;

public class MovieResponse {
	
	private int movieId;
	private String movieName;
	private Genres movieGenres;
	private Language movieLanguage;
	private LocalTime movieDuration;
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
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
