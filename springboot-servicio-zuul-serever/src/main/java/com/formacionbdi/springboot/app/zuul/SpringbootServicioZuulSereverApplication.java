package com.formacionbdi.springboot.app.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@EnableZuulProxy//zuul
@SpringBootApplication
public class SpringbootServicioZuulSereverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioZuulSereverApplication.class, args);
	}

}
