package springWebFlux.dao;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import springWebFlux.bean.Customer;

public interface CustomerR2dbcRepository
		extends R2dbcRepository<Customer, Long> {
}