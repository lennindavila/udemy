package com.formacionbdi.springboot.app.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//Esta anotacion @EnableEurekaClient es opcional cuando ya se agrego en el POM el "spring-cloud-starter-netflix-eureka-client", sin embargo es recomendable ponerla para indicar de manera explicita que sera cliente eureka
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class SpringbootServicioZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioZuulServerApplication.class, args);
	}

}
