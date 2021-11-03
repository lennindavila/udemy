package com.formacionbdi.springboot.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//la anotacion @EnableCircuitBreaker viene del artifact "spring-cloud-starter-netflix-hystrix", se encarga en otro hilo de monitorear la comunicacion entre los microservicios y manipular posibles fallos como latencia, timeout
@EnableCircuitBreaker

//Esta anotacion @EnableEurekaClient es opcional cuando ya se agrego en el POM el "spring-cloud-starter-netflix-eureka-client", sin embargo es recomendable ponerla para indicar de manera explicita que sera cliente eureka
@EnableEurekaClient

//La anotacion @RibbonClient no sera necesaria cuando pasamos a usar servidor eureka ya que se maneja automaticamente y ya se encuentra implicito
//@RibbonClient(name="servicio-productos")
@EnableFeignClients
@SpringBootApplication
//usualmente esta anotacion ya esta incluida en la anotacion de arriba pero en nuestro caso la sobreescribimos para hacer esa exclusion
@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class})
public class SpringbootServicioItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioItemApplication.class, args);
	}

}
