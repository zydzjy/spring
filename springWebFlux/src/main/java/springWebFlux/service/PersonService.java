<<<<<<< HEAD
package springWebFlux.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springWebFlux.bean.Employee;
import springWebFlux.bean.Person;

public interface PersonService {
	Flux<Person> findAll();
}
=======
package springWebFlux.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springWebFlux.bean.Employee;
import springWebFlux.bean.Person;

public interface PersonService {
	public Flux<Person> findAll();
}
>>>>>>> 49f8c4445bdd3ac99b29b4e622b9cb17970d9fcd
