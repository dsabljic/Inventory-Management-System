package com.inventory.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().load();
		System.setProperty("KEYSTORE_PASSWORD", dotenv.get("KEYSTORE_PASSWORD"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(BackendApplication.class, args);
	}

}
