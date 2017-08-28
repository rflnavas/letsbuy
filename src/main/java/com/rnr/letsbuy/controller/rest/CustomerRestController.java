package com.rnr.letsbuy.controller.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rnr.letsbuy.model.Customer;
import com.rnr.letsbuy.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping(path="/customer/{customerId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Resource<Customer> getCustomer(@PathVariable("customerId") Long id){
		Customer customer = customerService.findCustomer(id).orElse(new Customer());
		Resource<Customer> resource = 
				new Resource<Customer>(customer, 
						ControllerLinkBuilder.linkTo(methodOn(CustomerRestController.class).getCustomer(id))
						.withSelfRel());
		return resource;
	}
	
}
