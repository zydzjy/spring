package springWebFlux.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;
import springWebFlux.bean.Employee;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Integer> {
	@Query("{ 'name': ?0 }")
    Flux<Employee> findByName(final String name);
}