package com.imdb.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imdb.dto.ReviewRequest;
import com.imdb.dto.ReviewResponse;
import com.imdb.entity.Movie;
import com.imdb.entity.Review;
import com.imdb.entity.User;
import com.imdb.exception.MovieNotFoundByIdException;
import com.imdb.exception.ReviewNotFoundByIdException;
import com.imdb.exception.ReviewsDataNotPresentException;
import com.imdb.exception.UserNotFoundByIdException;
import com.imdb.repositary.MovieRepositary;
import com.imdb.repositary.ReviewRepositary;
import com.imdb.repositary.UserRepositary;
import com.imdb.service.ReviewService;
import com.imdb.util.ResponseStructure;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private ResponseStructure<ReviewResponse> structure;
	@Autowired
	private ReviewRepositary reviewRepo;
	@Autowired 
	private UserRepositary userRepo;
	@Autowired
	private MovieRepositary movieRepo;
	
	private Review convertToReview(ReviewRequest reviewRequest) {
		Review review=new Review();
		
		review.setReviewMessage(reviewRequest.getReviewMessage());
		review.setReviewRating(reviewRequest.getReviewRating());
		
		return review;
	}
	
	private ReviewResponse convertToReviewResponse(Review review2) {
		ReviewResponse reviewResponse=new ReviewResponse();
		
		reviewResponse.setReviewId(review2.getReviewId());
		reviewResponse.setReviewMessage(review2.getReviewMessage());
		reviewResponse.setReviewRating(review2.getReviewRating());
		return reviewResponse;
	}

	@Override
	public ResponseEntity<ResponseStructure<ReviewResponse>> addReview(ReviewRequest reviewRequest, int userId,
			int movieId) {
		Optional<User> useroptional = userRepo.findById(userId);
		Optional<Movie> movieOptional = movieRepo.findById(movieId);
		
		if(useroptional.isPresent())
		{
			if(movieOptional.isPresent())
			{
				Review review=convertToReview(reviewRequest);
				review.setUser(useroptional.get());
				review.setMovie(movieOptional.get());
				
				Review review2 = reviewRepo.save(review);
				
				ReviewResponse reviewResponse=convertToReviewResponse(review2);
				
				Map<String, String> hyperLink=new HashMap<>();
				hyperLink.put("users", "/users/"+review2.getUser().getUserId());
				hyperLink.put("movies", "/movies/"+review2.getMovie().getMovieId());
				
				
				reviewResponse.setOptions(hyperLink);
				
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setMessage("Review Data Added Successfully");
				structure.setData(reviewResponse);
				
				return new ResponseEntity<ResponseStructure<ReviewResponse>>(structure,HttpStatus.CREATED);
				
			}
			else
				throw new MovieNotFoundByIdException("Movie Not Present To add Review for a Given Movie Id");
		}
		else
			throw new UserNotFoundByIdException("User Not Present To Add Review For Given User Id");
	}

	@Override
	public ResponseEntity<ResponseStructure<ReviewResponse>> findReviewById(int reviewId) {
		
		Optional<Review> optional = reviewRepo.findById(reviewId);
		
		if(optional.isPresent())
		{
			Review review = optional.get();
			ReviewResponse reviewResponse = convertToReviewResponse(review);
			Map<String, String> hyperLink=new HashMap<>();
			hyperLink.put("movies", "/movies/"+review.getMovie().getMovieId());
			hyperLink.put("users", "/users/"+review.getUser().getUserId());
			
			reviewResponse.setOptions(hyperLink);
			
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Review Data Found for Given Id");
			structure.setData(reviewResponse);
			
			return new ResponseEntity<ResponseStructure<ReviewResponse>>(structure,HttpStatus.FOUND);
		}
		
		else {
			throw new ReviewNotFoundByIdException("Review Id Not Present");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<ReviewResponse>> updateReviewById(ReviewRequest reviewRequest,
			int reviewId) {
		Optional<Review> optional = reviewRepo.findById(reviewId);
		if(optional.isPresent())
		{
			Review oldReview = optional.get();
			
			Review review = convertToReview(reviewRequest);
			review.setMovie(oldReview.getMovie());
			review.setUser(oldReview.getUser());
			review.setReviewId(oldReview.getReviewId());
			
			
			Review review2 = reviewRepo.save(review);
			
			ReviewResponse reviewResponse = convertToReviewResponse(review2);
			Map<String, String> hyLink=new HashMap<>();
			hyLink.put("movies", "/movies/"+review2.getMovie().getMovieId());
			hyLink.put("users", "/users/"+review2.getUser().getUserId());
			reviewResponse.setOptions(hyLink);
			
			structure.setStatus(HttpStatus.ACCEPTED.value());
			structure.setMessage("Review Updated for an Given Review Id");
			structure.setData(reviewResponse);
			
			return new ResponseEntity<ResponseStructure<ReviewResponse>>(structure,HttpStatus.ACCEPTED);
		}
		else
			throw new ReviewNotFoundByIdException("Review Id not found to Update the data");
	}

	@Override
	public ResponseEntity<ResponseStructure<ReviewResponse>> deleteReviewById(int reviewId) {
		Optional<Review> optional = reviewRepo.findById(reviewId);
		if(optional.isPresent())
		{
			Review review = optional.get();
			reviewRepo.delete(review);
			ReviewResponse reviewResponse = convertToReviewResponse(review);
			
			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("Review Data Deleted..!!!");
			structure.setData(reviewResponse);
			
			return new ResponseEntity<ResponseStructure<ReviewResponse>>(structure,HttpStatus.OK);
		}
		
		else
			throw new ReviewNotFoundByIdException("Review Id not found to delete review data");
	}

	@Override
	public ResponseEntity<ResponseStructure<List<ReviewResponse>>> allReviews() {
		List<Review> reviewList = reviewRepo.findAll();
		if(!reviewList.isEmpty())
		{
			List<ReviewResponse> list=new ArrayList<>();
			for(Review review: reviewList)
			{
				ReviewResponse reviewResponse = convertToReviewResponse(review);
				Map<String, String> hyLink=new HashMap<>();
				hyLink.put("users","/users/"+review.getUser().getUserId());
				hyLink.put("movies", "/movies/"+review.getMovie().getMovieId());
				reviewResponse.setOptions(hyLink);
				list.add(reviewResponse);
			}
			ResponseStructure<List<ReviewResponse>> structure=new ResponseStructure<>();
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Review List Found");
			structure.setData(list);
			
			return new ResponseEntity<ResponseStructure<List<ReviewResponse>>>(structure,HttpStatus.FOUND);
			
		}
		else
			throw new ReviewsDataNotPresentException("No Review Data Present To Fetch");
	}

	@Override
	public ResponseEntity<ResponseStructure<List<ReviewResponse>>> findReviewByMovieId(int movieId) {
		Optional<Movie> optional = movieRepo.findById(movieId);
		if(optional.isPresent())
		{
			List<Review> reviewList = reviewRepo.findReviewByMovieId(movieId);
			
			List<ReviewResponse> list=new ArrayList<>();
			
			for(Review review : reviewList)
			{
				ReviewResponse reviewResponse = convertToReviewResponse(review);
				Map<String, String> hyLink=new HashMap<>();
				hyLink.put("users", "/users/"+review.getUser().getUserId());
				hyLink.put("movies", "/movies/"+review.getMovie().getMovieId());
				reviewResponse.setOptions(hyLink);
				list.add(reviewResponse);
			}
			ResponseStructure<List<ReviewResponse>> structure=new ResponseStructure<>();
			
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Review List Found For given Movie Id");
			structure.setData(list);
			
			return new ResponseEntity<ResponseStructure<List<ReviewResponse>>>(structure,HttpStatus.FOUND);
		}
		else
			throw new MovieNotFoundByIdException("Movie not found to fetch all Reviews for a given movie id");
	}
	

}
