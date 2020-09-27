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
	PersonRepository personRepo;
	
	@Override
	public Flux<Person> findAll() {
		return this.personRepo.findAll();
	}
}