package com.formacionbdi.springboot.app.item;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JAutoConfiguration;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

//Esta clase la vamos a utilizar para los beans con @Configuration
@Configuration
public class AppConfig {

	@Bean("clienteRest")
	@LoadBalanced//Esto se hace para utilizar Ribbon para rest template.
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer(){
		return factory -> factory.configureDefault(id->{
			return new Resilience4JConfigBuilder(id)
					.circuitBreakerConfig(CircuitBreakerConfig.custom()
					.slidingWindowSize(10) //cada diez peticiones mal
					.failureRateThreshold(50) //si el 50% se produce un error, entonces abre el circuito para que este servicio no responda
					.waitDurationInOpenState(Duration.ofSeconds(10l))//tras 10 llamadas en estado abierto, se pasa al estado semiabierto
					.permittedNumberOfCallsInHalfOpenState(5)
					//INI Llamadas lentas
					.slowCallRateThreshold(50) //Indicamos que si el 50% de las peticiones tardan más de 2 segundos (siguiente parametro)
					.slowCallDurationThreshold(Duration.ofSeconds(2L))//se considera llamada lenta y a la siguiente del 50% se pasa por el metodo alternativo
					//FIN Llamadas lentas
					.build()).timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(6L)).build()).build();
		});
		
		
	}
	
	
}
