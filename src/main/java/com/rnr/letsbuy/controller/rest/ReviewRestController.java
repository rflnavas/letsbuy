package com.rnr.letsbuy.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rnr.letsbuy.model.Review;
import com.rnr.letsbuy.service.ReviewService;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReviewRestController {

	@Autowired
	ReviewService reviewService;

	// @GetMapping(path="/restreview?custname={custname}", <-Error, do not
	// specify something like this, when refering request params
	// @Secured(value = { "ROLE_ADMIN", "ROLE_GUEST" })
	
	//It seems that the @Secured annotation has more power than WebSecurityConfigurerAdapter.configure
	
//	@Secured({ "ROLE_USER", "ROLE_GUEST" })
	@GetMapping(path = "/review")
	public List<Review> reviewsByCustomerName(@RequestParam(name = "custname", required = true) String name) {
		return reviewService.getReviewsByCustomerName(name);
	}

//	@Secured(value = { "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping("/reviews")
	public List<Review> getReviews() {
		List<Review> reviews = reviewService.getAllReviews();
		return reviews;
	}
	
	@RequestMapping(path="/review/{id}", method={RequestMethod.GET})
	public Review getReview(@PathVariable(name="id") Long id){
		Optional<Review> reviews = reviewService.findReview(id);
		return reviews.orElse(new Review());
	}
}
