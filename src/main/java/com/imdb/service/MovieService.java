package com.imdb.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.imdb.dto.MovieRequest;
import com.imdb.dto.MovieResponse;
import com.imdb.util.ResponseStructure;

public interface MovieService {
	
	public ResponseEntity<ResponseStructure<MovieResponse>> addMovie(MovieRequest movieRequest);
	public ResponseEntity<ResponseStructure<MovieResponse>> findMovieById(int movieId);
	public ResponseEntity<ResponseStructure<MovieResponse>> findMovieByName(String movieName);
	public ResponseEntity<ResponseStructure<List<MovieResponse>>> findMovieByGenres(String movieGenres);
	public ResponseEntity<ResponseStructure<List<MovieResponse>>> findAllMovies();
	public ResponseEntity<ResponseStructure<MovieResponse>> updateMovieById(MovieRequest movieRequest,int movieId);
	public ResponseEntity<ResponseStructure<MovieResponse>> deleteMovieById(int movieId);
	

}
