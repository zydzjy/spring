package springWebFlux.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springWebFlux.bean.Customer;
import springWebFlux.bean.Person;
import springWebFlux.dao.CustomerR2dbcRepository;
import springWebFlux.dao.PersonR2dbcRepository;
import springWebFlux.service.PersonService;

@Service("personServiceImpl")
public class PersonServiceImpl implements PersonService {
	@Autowired
	PersonR2dbcRepository personRepo;
	@Autowired
	CustomerR2dbcRepository customerRepo;

	@Override
	public Flux<Person> findAll() {
		return this.personRepo.findAll();
	}

	@Transactional
	@Override
	public Mono<Integer> savePerson(Person person) {
		System.out.println("save person...");
		Mono<Integer> personId = personRepo.save(person).map(it -> {
			if (it.getFirstname().equals("Dave")) {
				throw new IllegalStateException();
			} else {
				return it.getId();
			}
		}).doOnSuccess(id -> {
			Customer cust = new Customer();
			cust.setPersonId(id);
			cust.setFirstname("adsfasdf");
			this.customerRepo.save(cust).doOnError((e) -> {
				throw new IllegalStateException();
			}).subscribe();
		});
		return personId;
	}
}