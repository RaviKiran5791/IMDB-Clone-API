package com.imdb.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.imdb.dto.UserRequest;
import com.imdb.dto.UserResponse;
import com.imdb.util.ResponseStructure;

public interface UserService {
	
	public ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest);
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(int userId);
	public ResponseEntity<ResponseStructure<UserResponse>> findUserByEmail(String email);
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUsers();
	public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@RequestBody UserRequest userRequest,int userId);
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(int userId);
	
	
	
	

	
	
}
