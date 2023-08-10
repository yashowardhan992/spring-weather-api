package com.example.demo;

import com.example.demo.controller.WeatherService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeatherClientApplication {

	@Bean
	public WeatherService weatherService() {
		return new WeatherService();
	}

	public static void main(String[] args) {
		SpringApplication.run(WeatherClientApplication.class, args);
	}

}