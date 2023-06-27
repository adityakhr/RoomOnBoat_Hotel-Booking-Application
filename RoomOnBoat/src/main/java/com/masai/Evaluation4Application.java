package com.masai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
@EnableWebSecurity(debug = true)
@SpringBootApplication
public class Evaluation4Application {

	public static void main(String[] args) {
		SpringApplication.run(Evaluation4Application.class, args);
	}

}
