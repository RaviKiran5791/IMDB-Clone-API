package com.imdb.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.imdb.dto.ReviewRequest;
import com.imdb.dto.ReviewResponse;
import com.imdb.util.ResponseStructure;

public interface ReviewService {
	
	public ResponseEntity<ResponseStructure<ReviewResponse>> addReview(ReviewRequest reviewRequest,int userId,int movieId);
	public ResponseEntity<ResponseStructure<ReviewResponse>> findReviewById(int reviewId);
	public ResponseEntity<ResponseStructure<ReviewResponse>> updateReviewById(ReviewRequest reviewRequest,int reviewId);
	public ResponseEntity<ResponseStructure<ReviewResponse>> deleteReviewById(int reviewId);
	public ResponseEntity<ResponseStructure<List<ReviewResponse>>> allReviews();
	public ResponseEntity<ResponseStructure<List<ReviewResponse>>> findReviewByMovieId(int movieId);
	
	

}
