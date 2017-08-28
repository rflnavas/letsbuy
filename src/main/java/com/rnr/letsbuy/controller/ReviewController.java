package com.rnr.letsbuy.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rnr.letsbuy.model.Product;
import com.rnr.letsbuy.model.Review;
import com.rnr.letsbuy.service.ProductService;
import com.rnr.letsbuy.service.ReviewService;
import com.rnr.letsbuy.utils.SpringLogUtils;

@Controller
public class ReviewController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ReviewService reviewService;

	@Autowired
	ProductService productService;

	@RequestMapping(path = "/review/{idReview}")
	public String getReview(Model model, @PathVariable(name = "idReview") Long idReview) {
		model.addAttribute("pageTitle", "Reviews");

		// Optional<Review> review = reviewService.getReview(idReview);
		Optional<Review> review = reviewService.findReview(idReview);
		/*
		 * Due to my purpose is proving the exception handling, I intentionally
		 * put this so that if no review has been found, an exception may be
		 * thrown. In that case, this would lead us to the
		 * GlobalControllerAdvice.handleException
		 */
		model.addAttribute("review", review.get());
		return "views/review";
	}
	
	/*
	 * Trying to find out why redirect is not working from a DELETE HttpMethod in a controller to GET is not working(not refreshing page)
	 * but it works from GET to GET. Still working on it...
	 * 
	 * Note: The demoApp has a dummy example to show that the redirect from POST to a GET request really WORKS!
	 * ACTION TO VERIFIY: remove jQuery ajax handler and wrap the list of review under a form HTML tag
	 */
	@RequestMapping(path = "/product/{idProduct}/reviews", method={RequestMethod.DELETE, RequestMethod.GET})
	public String productReviews(HttpServletRequest request, 
			Model model, 
			@ModelAttribute("message") String message,
			@ModelAttribute("message2") String message2,
			@PathVariable(name = "idProduct") Long idProduct) {
		log.debug("Retrieving reviews for product : " + idProduct);
		SpringLogUtils.logFlashAttributes(request, model);
		
		Optional<Product> product = productService.find(idProduct);
		List<Review> reviews = product.isPresent() ? product.get().getReviews() : Collections.EMPTY_LIST;
		log.debug("Reviews for product (" + idProduct + ")" + reviews.toString());
		log.debug("No of reviews " + reviews.size());
		model.addAttribute("reviews", reviews);
		model.addAttribute("product", product.orElseGet(() -> null));
		model.addAttribute("message", message);
		model.addAttribute("message2", message2);
		return "views/reviews";
	}
	
	@RequestMapping(path = "/review/delete/{idReview}", method={RequestMethod.POST, RequestMethod.GET})
	public String deleteReview(@PathVariable(name = "idReview") Long id, 
			 @ModelAttribute("message") String message, Model model, HttpServletRequest request,
			 final RedirectAttributes redirectAttributes){
		Review review = reviewService.findReview(id).orElseGet(Review::new);
		
		String method = request.getMethod();
		if(StringUtils.equalsIgnoreCase("GET", method)){
			if(review.getId() != null){
				long idProd = review.getProduct().getId();
				reviewService.deleteReview(review);
				redirectAttributes.addFlashAttribute("message", "Review was deleted");
				model.addAttribute("message2", "Review was deleted");
				return "redirect:/product/" + idProd + "/reviews";
			}
			else{
				return "redirect:/showproducts";
			}
		} else{
			if(review.getId() != null){
				long idProd = review.getProduct().getId();
				reviewService.deleteReview(review);
				redirectAttributes.addFlashAttribute("message", "Review was deleted");
				model.addAttribute("message2", "Review was deleted");
				//return "redirect:/product/dummy";//IT WORKS!!!
				return "redirect:/product/" + idProd + "/reviews";
			}
			else{
				return "redirect:/products/showProducts";
			}
		}
		
	}
	
	@RequestMapping(path = "/product/dummy")
	public String productDummy(Model model, @ModelAttribute("message") String message,
			@ModelAttribute("message2") String message2){
		model.addAttribute("message", "Review was deleted 1");
		model.addAttribute("message2", "Review was deleted 2");
	
		return "views/dummy";
	}
	
	@RequestMapping(path = "/product/{idProduct}/reviews2")
	public String productReviews2(HttpServletRequest request, 
			Model model, 
			@ModelAttribute("message") String message,
			@ModelAttribute("message2") String message2,
			@PathVariable(name = "idProduct") Long idProduct) {
		log.debug("Retrieving reviews for product : " + idProduct);
		SpringLogUtils.logFlashAttributes(request, model);
		
		Optional<Product> product = productService.find(idProduct);
		List<Review> reviews = product.isPresent() ? product.get().getReviews() : Collections.EMPTY_LIST;
		log.debug("Reviews for product (" + idProduct + ")" + reviews.toString());
		log.debug("No of reviews " + reviews.size());
		model.addAttribute("reviews", reviews);
		model.addAttribute("product", product.orElseGet(() -> null));
		model.addAttribute("message", message);
		model.addAttribute("message2", message2);
		return "views/dummy";
	}

}
