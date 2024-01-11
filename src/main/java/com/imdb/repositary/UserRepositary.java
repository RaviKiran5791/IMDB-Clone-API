package com.imdb.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imdb.entity.User;

public interface UserRepositary extends JpaRepository<User, Integer>{
	
	public User findUserByEmail(String email);

}
