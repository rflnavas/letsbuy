package com.rnr.letsbuy.repository;

import org.springframework.data.repository.CrudRepository;

import com.rnr.letsbuy.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
