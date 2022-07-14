package com.formacionbdi.springboot.app.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//Esta clase la vamos a utilizar para los beans con @Configuration
@Configuration
public class AppConfig {

	@Bean("clienteRest")
	@LoadBalanced//Esto se hace para utilizar Ribbon para rest template.
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}
}
