package com.formacionbdi.springboot.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//Esta anotacion @EnableEurekaClient es opcional cuando ya se agrego en el POM el "spring-cloud-starter-netflix-eureka-client", sin embargo es recomendable ponerla para indicar de manera explicita que sera cliente eureka
@EnableEurekaClient   
@SpringBootApplication
@EntityScan("com.formacionbdi.springboot.app.commons.models.entity")
public class SpringbootServicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioProductosApplication.class, args);
	}

}
