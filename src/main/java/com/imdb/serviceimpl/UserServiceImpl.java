package com.imdb.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imdb.dto.UserRequest;
import com.imdb.dto.UserResponse;
import com.imdb.entity.User;
import com.imdb.exception.UserDataNotPresentException;
import com.imdb.exception.UserNotFoundByEmailException;
import com.imdb.exception.UserNotFoundByIdException;
import com.imdb.repositary.UserRepositary;
import com.imdb.service.UserService;
import com.imdb.util.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepositary userrepo;

	@Autowired
	private ResponseStructure<UserResponse> structure;

	private User convertToUser(UserRequest userRequest) {
		User user = new User();
		user.setUserName(userRequest.getUserName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		user.setUserDOB(userRequest.getUserDOB());

		return user;
	}

	private UserResponse convertToUserRespone(User user) {

		UserResponse userResponse = new UserResponse();

		userResponse.setUserId(user.getUserId());
		userResponse.setUserName(user.getUserName());
		userResponse.setEmail(user.getEmail());
		userResponse.setUserDOB(user.getUserDOB());

		return userResponse;
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest) {

		User user = convertToUser(userRequest);

		user = userrepo.save(user);

		UserResponse userRespone = convertToUserRespone(user);

		structure.setStatus(HttpStatus.CREATED.value());
		structure.setMessage("User Added");
		structure.setData(userRespone);

		return new ResponseEntity<ResponseStructure<UserResponse>>(structure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(int userId) {

		Optional<User> optional = userrepo.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();
			UserResponse userRespone = convertToUserRespone(user);

			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("User Found");
			structure.setData(userRespone);

			return new ResponseEntity<ResponseStructure<UserResponse>>(structure, HttpStatus.FOUND);
		}
		else 
		{
			throw new UserNotFoundByIdException("Id Not Found to Fetch User Data..!!!");	
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUsers() {
		List<User> userlist = userrepo.findAll();
		if (!userlist.isEmpty()) {
			List<UserResponse> list = new ArrayList<>();
			for (User user : userlist) {
				UserResponse userRespone = convertToUserRespone(user);
				list.add(userRespone);
			}
			ResponseStructure<List<UserResponse>> structure = new ResponseStructure<>();

			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("User List Found");
			structure.setData(list);

			return new ResponseEntity<ResponseStructure<List<UserResponse>>>(structure, HttpStatus.FOUND);

		} 
		else
			throw new UserDataNotPresentException("No User Data Presenr..!!!!");

			
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(UserRequest userRequest, int userId) {

		Optional<User> optional = userrepo.findById(userId);

		if (optional.isPresent()) {
			User olduser = optional.get();
			User user = convertToUser(userRequest);
			user.setUserId(olduser.getUserId());

			User user2 = userrepo.save(user);

			UserResponse userRespone = convertToUserRespone(user2);

			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("User Info Updated Successfully");
			structure.setData(userRespone);

			return new ResponseEntity<ResponseStructure<UserResponse>>(structure, HttpStatus.OK);
		}

		else {
			throw new UserNotFoundByIdException("Id Not Found to Update the User Data !!");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById(int userId) {

		Optional<User> optional = userrepo.findById(userId);
		if (optional.isPresent()) {
			User user = optional.get();

			userrepo.delete(user);

			UserResponse userRespone = convertToUserRespone(user);

			structure.setStatus(HttpStatus.OK.value());
			structure.setMessage("User Data Deleted Successfully");
			structure.setData(userRespone);

			return new ResponseEntity<ResponseStructure<UserResponse>>(structure, HttpStatus.OK);

		}
		else
			throw new UserNotFoundByIdException("Id NotFound to Delete the User !!");
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUserByEmail(String email) {

		User user = userrepo.findUserByEmail(email);

		if(user!=null)
		{
			UserResponse userRespone = convertToUserRespone(user);

			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("User Data Found");
			structure.setData(userRespone);

			return new ResponseEntity<ResponseStructure<UserResponse>>(structure,HttpStatus.FOUND);
		}
		else
		{
			throw new UserNotFoundByEmailException("Email Not Found to Fetch the User Data..!!!");
		}
	}

}
