package com.rnr.letsbuy.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rnr.letsbuy.model.Order;
import com.rnr.letsbuy.repository.OrderJDBCRepository;

@Service
public class OrderJDBCService {
	
	@Autowired
	OrderJDBCRepository orderRepository;
	
	public Order findFirstOrder(){
		return orderRepository.findFirst();
	}
	
	public Order findOrderById(Long id){
		return orderRepository.findById(id);
	}
	
	public Set<Order> findAll(){
		return orderRepository.findAllOrderByTimestampOnDesc();
	}
}
