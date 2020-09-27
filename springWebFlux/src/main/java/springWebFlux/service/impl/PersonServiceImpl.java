package springWebFlux.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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
	
	@Transactional
	@Override
	public Mono<Long> savePerson(Person person) throws Exception {
		System.out.println("save person...");
		personRepo.save(person).map(it -> {
			if (it.getFirstname().equals("Dave")) {
				throw new IllegalStateException();
			} else {
				return it;
			}
		})
		;
		return null;
	}
}