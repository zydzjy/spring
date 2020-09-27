package springWebFlux.dao;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import springWebFlux.bean.Person;

public interface PersonR2dbcRepository 
		extends R2dbcRepository<Person, Long> {
	
	@Query("select id, firstname, lastname from person c where c.lastname = :lastname")
	Flux<Person> findByLastname(String lastname);
}