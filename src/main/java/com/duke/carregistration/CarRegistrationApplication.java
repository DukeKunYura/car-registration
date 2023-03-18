package com.duke.carregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRegistrationApplication.class, args);
	}

	//for global cors-policy
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:8085");
//			}
//		};
//	}

}
