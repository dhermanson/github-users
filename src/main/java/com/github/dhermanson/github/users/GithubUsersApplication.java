package com.github.dhermanson.github.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GithubUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubUsersApplication.class, args);
	}

}
