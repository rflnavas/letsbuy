package com.rnr.letsbuy.controller.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rnr.letsbuy.model.Order;
import com.rnr.letsbuy.service.OrderJDBCService;

/**
 * This a rest controller that follows the HATEOAS principle
 * 
 * @author rafael.navas.ruiz
 *
 */
@RestController
@RequestMapping("/api")
public class OrderRestController {
	@Autowired
	private OrderJDBCService orderService;

	@GetMapping(path = "/orders/first", produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<Order> showFirstOrder() {
		Order order = orderService.findFirstOrder();
		Resource<Order> resource = new Resource<>(order);
		resource.add(linkTo(methodOn(OrderRestController.class).showFirstOrder()).withSelfRel());
		return resource;
	}

//	@GetMapping(path = "/orders/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Resource<Order> findOrder(@PathVariable("orderId") Long id) {
//		Order order = orderService.findOrderById(id);
//		Resource<Order> resource = new Resource<>(order);
//		resource.add(linkTo(methodOn(OrderRestController.class).findOrder(id)).withSelfRel());
//		resource.add(linkTo(methodOn(CustomerRestController.class)
//				.getCustomer(order.getCustomer().longValue())).withSelfRel());
//		return resource;
//
//	}
//	
//	@GetMapping(path = "/orders/", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Resources<Order> getAllOrders(){
//		Set<Order> orders = orderService.findAll();
//		/*
//		 * Note that this time we take Resource[S] instead of Resource.
//		 * Otherwise, if we intend to use a Collection in a Resource class,
//		 * Spring will throw an exception during Resource's instantiation
//		 */
//		final Resources<Order> resOrders = new Resources<>(orders);
//		orders.stream().forEach((o)->{
//			/*
//			 * The first two lines will feed the _links JSON property right after the _embedded property
//			 */
////			resOrders.add(linkTo(methodOn(OrderRestController.class).findOrder(o.getId())).withSelfRel());
//			resOrders.add(linkTo(methodOn(ProductRestController.class).getProduct(o.getProduct())).withRel("products"));
//			//resOrders.add(linkTo(methodOn(ProductRestController.class).getProduct(o.getProduct())).withRel(rel));
//		});
//		
//		return resOrders;
//	}
}
