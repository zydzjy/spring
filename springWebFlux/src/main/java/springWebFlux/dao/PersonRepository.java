package springWebFlux.dao;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import springWebFlux.bean.Person;

public interface PersonRepository extends R2dbcRepository<Person, Long> {

}
