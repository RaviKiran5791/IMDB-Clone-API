package com.imdb.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imdb.entity.Review;

public interface ReviewRepositary extends JpaRepository<Review, Integer>{
	
	@Query("select r from Review r where r.movie.movieId=?1")
	public List<Review> findReviewByMovieId(int movieId);

}
