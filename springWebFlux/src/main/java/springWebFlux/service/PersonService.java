package springWebFlux.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springWebFlux.bean.Person;

public interface PersonService {
	public Flux<Person> findAll();
	public Mono<Long> savePerson(Person person) throws Exception;
}