package springCloudCircuitBreaker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import springCloudCircuitBreaker.service.TestService;

@Service
public class ServiceResillenceImpl implements TestService {
	
	private RestTemplate rest;
	@Autowired
	private CircuitBreakerFactory<?, ?> slowCustomizer;

	public ServiceResillenceImpl(RestTemplate rest, CircuitBreakerFactory<?, ?> cbFactory) {
		this.rest = rest;
		this.slowCustomizer = cbFactory;
	}
	
	@Override
	public String slow() {
		return slowCustomizer.create("slow").run(() -> 
			rest.getForObject("http://localhost:8080/test2", String.class), 
			throwable -> "fallback");
	}
}
