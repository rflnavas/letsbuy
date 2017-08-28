package com.rnr.letsbuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rnr.letsbuy.model.Order;
import com.rnr.letsbuy.service.OrderService;
@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(path="/orders/all")
	public String showOrders(Model model){
		List<Order> orders = orderService.listAllOrders();
		model.addAttribute("orders", orders);
		return "views/orders";
	}
}
