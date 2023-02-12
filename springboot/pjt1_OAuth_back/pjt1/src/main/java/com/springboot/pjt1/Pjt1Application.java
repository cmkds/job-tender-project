package com.springboot.pjt1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Pjt1Application {
	public static final String APPLICATION_LOCATIONS = "spring.config.location=" + "classpath:application.yml";
	public static void main(String[] args) {
		SpringApplication.run(Pjt1Application.class, args);
	}

}
