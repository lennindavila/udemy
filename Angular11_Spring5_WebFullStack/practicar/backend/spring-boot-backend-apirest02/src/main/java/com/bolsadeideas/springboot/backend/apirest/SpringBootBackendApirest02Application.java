package com.bolsadeideas.springboot.backend.apirest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootBackendApirest02Application implements CommandLineRunner {
	
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendApirest02Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		for(int i = 0;i<4;i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.err.println(passwordBcrypt);
		}
	}
}
