package com.formacionbdi.springboot.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//Lo comentamos para utilizar el balanceo de carga de eureka.
//@RibbonClient("servicio-productos")
@SpringBootApplication
@EnableFeignClients //con esta anotación utilizamos la alternativa a RestTemplate, que es Feign
@EnableEurekaClient
public class SpringbootServicioItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioItemApplication.class, args);
	}

}
