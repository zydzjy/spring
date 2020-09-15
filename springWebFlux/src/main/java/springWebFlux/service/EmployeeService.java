package springWebFlux.service;

import reactor.core.publisher.Mono;
import springWebFlux.bean.Employee;

public interface EmployeeService {
	Mono<Employee> findById(Integer id);

	void create(Employee e);
}
