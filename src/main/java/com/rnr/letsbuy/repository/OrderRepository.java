package com.rnr.letsbuy.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.rnr.letsbuy.model.Order;

/*
 * This is a way to define a customized name for rest repository so that we could request with the following url:
 * <host>/orderedproducts instead <host>/orders. Remember that, by default, Spring uses the plural for every rest repository. 
 * 
 * Use postman to find out the different links that are generated in the response. 
 * i.e: http://localhost:8080/orderedproducts/search
 */
@RepositoryRestResource(path = "orderedproducts")
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

	List<Order> findByTimestampBetween(@Param("from") @DateTimeFormat(iso=ISO.DATE) Date start,
			@Param("to") @DateTimeFormat(iso=ISO.DATE) Date end);
	
	Order findByOrderId(@Param("orderId") String id);
}
