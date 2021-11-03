package com.formacionbdi.springboot.app.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	@Bean("clienteRest")
	@LoadBalanced //Con esto RestTemplate va a usar Ribbon para balanceo de carga, y el resttemplate buscara la mejor instancia disponible
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}
}
