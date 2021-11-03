package com.bolsadeideas.springboot.backend.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootBackendApirestApplication implements CommandLineRunner{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendApirestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String var ="strawberries";
		System.out.print(var.substring(2,5));
		
		StringBuilder sb = new StringBuilder("ggg");
				sb.deleteCharAt(0).insert(0, "H").append(" www");
		
				try {
					throw Error;
				}catch(Exception e) {
					
				}
				
		
		String password = "12345";
		for(int i = 0; i < 4; i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println(passwordBcrypt);
		}		
	}
}
