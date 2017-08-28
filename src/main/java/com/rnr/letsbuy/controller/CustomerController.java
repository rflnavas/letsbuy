package com.rnr.letsbuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rnr.letsbuy.model.Customer;
import com.rnr.letsbuy.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(path="/customer/{idCustomer}")
	public String customerDetail(@PathVariable(name="idCustomer") Long id, Model model){
		Customer customer = customerService.findCustomer(id).orElse(new Customer());
		model.addAttribute("customer", customer);
		return "views/customerDetail";
	}
}
