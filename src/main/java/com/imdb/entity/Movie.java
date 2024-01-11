package com.imdb.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.imdb.enums.Genres;
import com.imdb.enums.Language;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Movies")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;
	private String movieName;
	private Genres movieGenres;
	private Language movieLanguage;
	private LocalTime movieDuration;
	
	@OneToMany(mappedBy = "movie",fetch = FetchType.EAGER)
	private List<Review> movieReviewList=new ArrayList<>();
	
	
	public LocalTime getMovieDuration() {
		return movieDuration;
	}
	public void setMovieDuration(LocalTime movieDuration) {
		this.movieDuration = movieDuration;
	}
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
	public List<Review> getMovieReviewList() {
		return movieReviewList;
	}
	public void setMovieReviewList(List<Review> movieReviewList) {
		this.movieReviewList = movieReviewList;
	}
	
	
}
