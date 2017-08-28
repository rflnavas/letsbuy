package com.rnr.letsbuy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rnr.letsbuy.model.Customer;
import com.rnr.letsbuy.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Optional<Customer> findCustomer(Long id){
		return Optional.ofNullable(customerRepository.findOne(id));
	}
	
}
