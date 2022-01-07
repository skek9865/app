package com.meet.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class AppApplication {

<<<<<<< HEAD
//	public static final String APPLICATION_LOCATIONS = "spring.config.location="
//			+ "classpath:application.properties,"
=======
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.properties";
>>>>>>> master
//			+ "classpath:aws.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(AppApplication.class)
//				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

}
