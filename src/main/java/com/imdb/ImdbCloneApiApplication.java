package com.imdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImdbCloneApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImdbCloneApiApplication.class, args);
		System.out.println("IMDB Clone API");
	}

}
