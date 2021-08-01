package com.example.springredisapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringRedisAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisAppApplication.class, args);
	}

}
