package springWebFlux.service;

import reactor.core.publisher.Flux;
import springWebFlux.bean.Person;

public interface PersonService {
	public Flux<Person> findAll();
}