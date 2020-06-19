package com.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CentralErrorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralErrorApplication.class, args);
	}

}
