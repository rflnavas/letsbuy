package com.rnr.letsbuy.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rnr.letsbuy.model.Product;
import com.rnr.letsbuy.repository.ProductRepository;

@Service
public class ProductService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductRepository productRepository;
	
	public List<Product> findAll(){
		log.info(">>>finding all products");
		return productRepository.findAllByOrderByNameAsc();
	}
	
	public List<Product> findAllOrderByPrice(){
		log.info(">>>finding all products");
		return productRepository.findAllByOrderByPriceAsc();
	}
	
	public Optional<Product> find(Long id){
		return Optional.ofNullable(productRepository.findOne(id));
	}
	
	public Product save(Product product){
		return productRepository.save(product);
	}
	
	public List<Product> productsByKeywords(String[] keywords){
		List<String> keywordsList = new ArrayList<>(keywords.length);
		Arrays.stream(keywords).forEach((s) -> keywordsList.add(s.toUpperCase()));
		return productRepository.findAllByTagsIn(keywordsList);
	}
	
	/*
	public Optional<Product> getProduct(Long id){
		log.debug("Accessing database to retrieve reviews for a given product");
		return Database.getProducts().stream()
				.filter(p -> p.getId().equals(id))
				.findFirst();
	}
	*/

	public List<Product> productsByNameAndPriceFrom(String name, double priceFrom) {
		return productRepository.findProductsByNameAndPriceFrom(name, priceFrom);
	}
}
