package com.imdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imdb.dto.MovieRequest;
import com.imdb.dto.MovieResponse;
import com.imdb.service.MovieService;
import com.imdb.util.ResponseStructure;

@RestController
public class MovieController {
	@Autowired
	private MovieService movieService;
	
	@PostMapping("/movies")
	public ResponseEntity<ResponseStructure<MovieResponse>> addMovie(@RequestBody MovieRequest movieRequest){
		return movieService.addMovie(movieRequest);
	}
	
	@GetMapping("/movies/{movieId}")
	public ResponseEntity<ResponseStructure<MovieResponse>> findMovieById(@PathVariable int movieId){
		return movieService.findMovieById(movieId);
	}
	@GetMapping("/movie-name/{movieName}/movies")
	public ResponseEntity<ResponseStructure<MovieResponse>> findMovieByName(@PathVariable String movieName){
		return movieService.findMovieByName(movieName);
	}
	@GetMapping("/movie-genres/{movieGenres}/movies")
	public ResponseEntity<ResponseStructure<List<MovieResponse>>> findMovieByGenres(@PathVariable String movieGenres){
		return movieService.findMovieByGenres(movieGenres);
	}
	@GetMapping("/movies")
	public ResponseEntity<ResponseStructure<List<MovieResponse>>> findAllMovies(){
		return movieService.findAllMovies();
	}
	@PutMapping("/movies/{movieId}")
	public ResponseEntity<ResponseStructure<MovieResponse>> updateMovieById(@RequestBody MovieRequest movieRequest,@PathVariable int movieId){
		return movieService.updateMovieById(movieRequest, movieId);
	}
	@DeleteMapping("/movies/{movieId}")
	public ResponseEntity<ResponseStructure<MovieResponse>> deleteMovieById(@PathVariable int movieId){
		return movieService.deleteMovieById(movieId);
	}
	

}
