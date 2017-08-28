package com.rnr.letsbuy.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rnr.letsbuy.model.Review;
import com.rnr.letsbuy.repository.ReviewRepository;

@Service
public class ReviewService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ReviewRepository reviewRepository;

	public ReviewService() {
	}

	public List<Review> getReviewsForProduct(Long id) {
		log.info("Retrieving all reviews for product : " + id);
		return null;
	}

	public List<Review> getReviewsByCustomerName(String customerName) {
		log.info("Retrieving all reviews from customer : " + customerName);
		return reviewRepository.findAllByCustomerNameIgnoreCase(customerName);
	}

	public List<Review> getAllReviews() {
		log.info("Retrieving all reviews");
		return reviewRepository.findAll();
	}

	public Optional<Review> findReview(Long id) {
		return Optional.ofNullable(reviewRepository.findOne(id));
	}
	
	public void deleteReview(Long id){
		log.debug("Review " + id + " is about to be deleted");
		reviewRepository.delete(id);
	}
	
	public void deleteReview(Review review){
		log.debug("Review " + review + " is about to be deleted");
		reviewRepository.delete(review);
	}

	/*
	 * public List<Review> getReviewsFromProduct(Long id){
	 * log.debug("Accesing database to retrieve reviews for a given product");
	 * return Database.getReviews().stream() .filter(r ->
	 * r.getProduct().getId().equals(id)) .collect(Collectors.toList()); }
	 * 
	 * public Optional<Review> getReview(Long id){ log.debug("Getting review");
	 * return Database.getReviews().stream().filter(r->
	 * r.getId().equals(id)).findFirst(); }
	 */

}
