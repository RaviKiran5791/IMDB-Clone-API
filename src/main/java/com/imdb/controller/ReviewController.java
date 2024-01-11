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
import org.springframework.web.bind.annotation.RestController;

import com.imdb.dto.ReviewRequest;
import com.imdb.dto.ReviewResponse;
import com.imdb.service.ReviewService;
import com.imdb.util.ResponseStructure;

@RestController
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/{userId}/{movieId}/reviews")
	public ResponseEntity<ResponseStructure<ReviewResponse>> addReview(@RequestBody ReviewRequest reviewRequest,@PathVariable int userId,@PathVariable int movieId){
		return reviewService.addReview(reviewRequest, userId, movieId);
	}
	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<ResponseStructure<ReviewResponse>> findReviewById(@PathVariable int reviewId){
		return reviewService.findReviewById(reviewId);
	}

	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<ResponseStructure<ReviewResponse>> updateReviewById(@RequestBody ReviewRequest reviewRequest,@PathVariable int reviewId){
		return reviewService.updateReviewById(reviewRequest, reviewId);
	}
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<ResponseStructure<ReviewResponse>> deleteReviewById(@PathVariable int reviewId){
		return reviewService.deleteReviewById(reviewId);
	}
	@GetMapping("/reviews")
	public ResponseEntity<ResponseStructure<List<ReviewResponse>>> allReviews(){
		return reviewService.allReviews();
	}
	@GetMapping("/{movieId}/reviews")
	public ResponseEntity<ResponseStructure<List<ReviewResponse>>> findReviewByMovieId(@PathVariable int movieId){
		return reviewService.findReviewByMovieId(movieId);
	}
}
