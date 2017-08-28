package com.rnr.letsbuy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rnr.letsbuy.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	List<Review> findAllByCustomerNameIgnoreCase(String name);

	List<Review> findAll();
}
