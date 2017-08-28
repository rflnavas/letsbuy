
package com.rnr.letsbuy.model.projection;

import org.springframework.data.rest.core.config.Projection;

import com.rnr.letsbuy.model.Order;

/*
 * This is the way we can override the Spring's default setting for exposing attributes in the response. We'll notice 
 * that a new parameter, whose name is projection, is possible to be applied.
 * This can be checked by accessing
 * http://localhost:8080/orderedproducts/2?projection=order
 */
@Projection(name = "order", types = { Order.class })
public interface OrderProjection {
	
	/*
	 * This should not be exposed as Spring does. Only for testing the functionality
	 */
	String getId();

	String getOrderId();
}