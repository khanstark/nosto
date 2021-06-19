package com.example.exchanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ExchangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangerApplication.class, args);
	}

}
