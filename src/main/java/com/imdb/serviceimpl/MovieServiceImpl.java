package com.imdb.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imdb.dto.MovieRequest;
import com.imdb.dto.MovieResponse;
import com.imdb.entity.Movie;
import com.imdb.enums.Genres;
import com.imdb.exception.MoviDataNotPresentException;
import com.imdb.exception.MovieNotFoundByIdException;
import com.imdb.exception.MovieNotFoundByMovieNameException;
import com.imdb.repositary.MovieRepositary;
import com.imdb.service.MovieService;
import com.imdb.util.ResponseStructure;
@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private MovieRepositary repo;
	@Autowired
	private ResponseStructure<MovieResponse> structure;

	private Movie convertToMovie(MovieRequest movieRequest) {
		
		Movie movie=new Movie();
		
		movie.setMovieName(movieRequest.getMovieName());
		movie.setMovieGenres(movieRequest.getMovieGenres());
		movie.setMovieLanguage(movieRequest.getMovieLanguage());
		movie.setMovieDuration(movieRequest.getMovieDuration());
		movie.setMovieReviewList(movieRequest.getMovieReviewList());
		
		return movie;
	}
	private MovieResponse convertToMovieResponse(Movie movie) {
		
		MovieResponse movieResponse=new MovieResponse();
		
		movieResponse.setMovieId(movie.getMovieId());
		movieResponse.setMovieName(movie.getMovieName());
		movieResponse.setMovieLanguage(movie.getMovieLanguage());
		movieResponse.setMovieGenres(movie.getMovieGenres());
		movieResponse.setMovieDuration(movie.getMovieDuration());
		
		return movieResponse;
	}
	@Override
	public ResponseEntity<ResponseStructure<MovieResponse>> addMovie(MovieRequest movieRequest) {
		
		Movie movie=convertToMovie(movieRequest);
		movie = repo.save(movie);
		MovieResponse movieResponse=convertToMovieResponse(movie);
		
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("Movie Added");
		structure.setData(movieResponse);
		
		return new ResponseEntity<ResponseStructure<MovieResponse>>(structure,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<MovieResponse>> findMovieById(int movieId) {
		Optional<Movie> optional = repo.findById(movieId);
		if(optional.isPresent())
		{
			Movie movie = optional.get();
			MovieResponse movieResponse = convertToMovieResponse(movie);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Movie Data Found");
			structure.setData(movieResponse);
			
			return new ResponseEntity<ResponseStructure<MovieResponse>>(structure,HttpStatus.FOUND);
		}
		else
			throw new MovieNotFoundByIdException("Id not Found to fetch Movie Data");
	}

	@Override
	public ResponseEntity<ResponseStructure<MovieResponse>> findMovieByName(String movieName) {
		Movie movie = repo.findMovieByMovieName(movieName);
		if(movie!=null)
		{
			MovieResponse movieResponse = convertToMovieResponse(movie);
			
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Movie Data Found");
			structure.setData(movieResponse);
			
			return new ResponseEntity<ResponseStructure<MovieResponse>>(structure,HttpStatus.FOUND);
		}
		else
			throw new MovieNotFoundByMovieNameException("Movie Name Not found to fetch Movie Date");
	}

	@Override
	public ResponseEntity<ResponseStructure<List<MovieResponse>>> findAllMovies() {
		List<Movie> movieList = repo.findAll();
		if(!movieList.isEmpty())
		{
			List<MovieResponse> list=new ArrayList<>();
			for(Movie movie : movieList)
			{
				MovieResponse movieResponse = convertToMovieResponse(movie);
				list.add(movieResponse);
			}
			ResponseStructure<List<MovieResponse>> structure=new ResponseStructure<>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Movie List Found");
			structure.setData(list);
			
			return new ResponseEntity<ResponseStructure<List<MovieResponse>>>(structure,HttpStatus.FOUND);
		}
		else 
				throw new MoviDataNotPresentException("No Movie Data Present..!!!");
	}

	@Override
	public ResponseEntity<ResponseStructure<MovieResponse>> updateMovieById(MovieRequest movieRequest, int movieId) {
		Optional<Movie> optional = repo.findById(movieId);
		if(optional.isPresent())
		{
			Movie oldmovie = optional.get();	
			Movie movie = convertToMovie(movieRequest);
			movie.setMovieId(oldmovie.getMovieId());
			
			Movie movie2 = repo.save(movie);
			
			MovieResponse movieResponse = convertToMovieResponse(movie2);
			
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Movie Data Updated Successfully");
			structure.setData(movieResponse);
			
			return new ResponseEntity<ResponseStructure<MovieResponse>>(structure,HttpStatus.OK);
			
		}
		else
			throw new MovieNotFoundByIdException("Movie Not Found to Update The Movie Data");
	}

	@Override
	public ResponseEntity<ResponseStructure<MovieResponse>> deleteMovieById(int movieId) {
		Optional<Movie> optional = repo.findById(movieId);
		if(optional.isPresent())
		{
			Movie movie = optional.get();
			
			repo.delete(movie);
			
			MovieResponse movieResponse = convertToMovieResponse(movie);
			
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Movie Data Deleted Successfully");
			structure.setData(movieResponse);
			
			return new ResponseEntity<ResponseStructure<MovieResponse>>(structure,HttpStatus.OK);
			
		}
		else
			throw new MovieNotFoundByIdException("Movie Id Not Found to Delete the Movie Data");
	}
	@Override
	public ResponseEntity<ResponseStructure<List<MovieResponse>>> findMovieByGenres(String movieGenres) {
		Genres genres = Genres.valueOf(movieGenres);
		
		List<Movie> movieList = repo.findMovieByMovieGenres(genres);
		
		if(!movieList.isEmpty())
		{
			List<MovieResponse> list=new ArrayList<>();
			
			for(Movie movie : movieList)
			{
				MovieResponse movieResponse = convertToMovieResponse(movie);
				list.add(movieResponse);
			}
			ResponseStructure<List<MovieResponse>> structure=new ResponseStructure<>();
			
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Movie List Found On Given Genres");
			structure.setData(list);
			
			return new ResponseEntity<ResponseStructure<List<MovieResponse>>>(structure,HttpStatus.FOUND);
		}
		else
			throw new MoviDataNotPresentException("No Movie Data present on "+genres+" ..!!!");
		
		
		
	}

}
