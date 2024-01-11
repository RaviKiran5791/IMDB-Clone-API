package com.imdb.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imdb.entity.Movie;
import com.imdb.enums.Genres;

public interface MovieRepositary extends JpaRepository<Movie, Integer>{
	
	public Movie findMovieByMovieName(String movieName);
	
	public List<Movie> findMovieByMovieGenres(Genres movieGenres);

}
