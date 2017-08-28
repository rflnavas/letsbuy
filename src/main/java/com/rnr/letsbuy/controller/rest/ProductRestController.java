package com.rnr.letsbuy.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rnr.letsbuy.exception.NoItemFoundException;
import com.rnr.letsbuy.model.Product;
import com.rnr.letsbuy.service.ProductService;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProductRestController {

	@Autowired
	private ProductService productService;

	/**
	 * Infinitely marshalls JSON for Product. WHY? Solution :
	 * {@link com.rnr.letsbuy.model.Product}
	 */
	@GetMapping(path = "/product/{productId}")
	public Resource<Product> getProduct(@PathVariable("productId") Long id) {
		Product product = productService.find(id)
				.orElseThrow(() -> new NoItemFoundException("No product with " + id + " found"));
		Resource<Product> resProd = new Resource<Product>(product, ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(ProductRestController.class).getProduct(id)).withSelfRel());
		return resProd;
	}

	/**
	 * Find product by tags. Note that tag accepts a multivalued parameter.
	 * 
	 * @param tag
	 * @return
	 */
	@GetMapping(path = "/product")
	public Resources<Product> getProducts(
			@RequestParam(name = "tag", required = false, defaultValue = "") String... tag) {
		List<Product> products = null;
		if (tag != null && tag.length > 0) {
			products = productService.productsByKeywords(tag);
		} else {
			products = productService.findAll();

		}
		Resources<Product> resProducts = new Resources<>(products, ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(ProductRestController.class).getProducts()).withSelfRel());
		return resProducts;
	}

	@GetMapping(path = "/product/filter")
	public List<Product> findByCriteria(@RequestParam(name = "name") String name,
			@RequestParam(name = "priceFrom") double price) {
		return productService.productsByNameAndPriceFrom(name, price);
	}

	/*
	 * For trial purpose while trying to figure out the infinite recursion in
	 * marshalling JSON. Executed with no problem :-)
	 */
	@GetMapping(path = "text/product/{productId}")
	public String getProductPlainText(@PathVariable("productId") Long id) {
		// Product product = productService.find(id).orElse(new Product());
		Product product = productService.find(id).get();
		return product.toString();
	}
}
