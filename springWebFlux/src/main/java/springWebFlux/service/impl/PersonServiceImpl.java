<<<<<<< HEAD
package springWebFlux.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springWebFlux.bean.Employee;
import springWebFlux.bean.Person;
import springWebFlux.dao.PersonRepository;
import springWebFlux.service.PersonService;

@Service("personServiceImpl")
public class PersonServiceImpl implements PersonService {
	
	@Autowired 
	private PersonRepository repository; 
	
	@Override
	public Flux<Person> findAll() {
		return this.repository.findAll();
	}
}
=======
package springWebFlux.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springWebFlux.bean.Employee;
import springWebFlux.bean.Person;
import springWebFlux.dao.PersonR2dbcRepository;
import springWebFlux.service.PersonService;

@Service("personServiceImpl")
public class PersonServiceImpl implements PersonService {
	@Autowired 
	PersonR2dbcRepository personRepo;
	@Override
	public Flux<Person> findAll() {
		return this.personRepo.findAll();
	}
}
>>>>>>> 49f8c4445bdd3ac99b29b4e622b9cb17970d9fcd
