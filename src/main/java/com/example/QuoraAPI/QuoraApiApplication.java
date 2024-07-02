package com.example.QuoraAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuoraApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuoraApiApplication.class, args);
	}

}
