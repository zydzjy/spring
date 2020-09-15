package springWebFlux.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import springWebFlux.bean.Employee;
import springWebFlux.dao.EmployeeRepository;
import springWebFlux.service.EmployeeService;

@Service("employeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepo;

	@Override
	public Mono<Employee> findById(Integer id) {
		return employeeRepo.findById(id);
	}

	@Override
	public void create(Employee e) {
	    employeeRepo.save(e).subscribe();
	}
}