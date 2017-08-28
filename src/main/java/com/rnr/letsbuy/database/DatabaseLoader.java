package com.rnr.letsbuy.database;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rnr.letsbuy.model.Customer;
import com.rnr.letsbuy.model.Product;
import com.rnr.letsbuy.model.Review;
import com.rnr.letsbuy.model.Review.Rate;
import com.rnr.letsbuy.model.Review.ReviewBuilder;
import com.rnr.letsbuy.utils.DateFormatUtils;

/**
 * We no longer use this approach in favour of taking advantage of a real in-memory database; H2
 * @author rafael.navas.ruiz
 *
 */
@Deprecated
public class DatabaseLoader {
	
	private static List<Product> products = new ArrayList<>();
	private static List<Customer> customers = new ArrayList<>();
	private static List<Review> reviews = new ArrayList<>();
	
	static{
		
		Product p1 = new Product(10L, "King PC", BigDecimal.valueOf(1429.99));
		Product p2 = new Product(11L, "Selfie stick", BigDecimal.valueOf(7.99));
		products.add(p1);
		products.add(p2);
		Customer c1, c2, c3;
		
		c1 = new Customer(1L, "Robert", "robd2000@gmail.com", DateFormatUtils.formatDate("04/07/2017"));
		c2 = new Customer(2L, "Pierre", "pierrefontaine@yahoo.com", DateFormatUtils.formatDate("06/07/2017"));
		c3 = new Customer(3L, "Alex", "jandro@hotmail.com", DateFormatUtils.formatDate("11/07/2017"));
		
		
		customers.add(c1);
		customers.add(c2);
		customers.add(c3);
		
		Review r1 = ReviewBuilder.create(20L, p1, c1)
				.title("King PC rocks!")
				.body("This computer is as fast as hell")
				.rate(Rate.EXCELLENT.score())
				.date(DateFormatUtils.formatDate("14/07/2017"))
				.build();
		
		Review r2 = ReviewBuilder.create(21L, p1, c2)
				.title("Can now play games that require loads of resources")
				.body("It's so powerful that I finally can play games in 4K")
				.rate(Rate.EXCELLENT.score())
				.date(DateFormatUtils.formatDate("20/07/2017"))
				.build();
		Review r3 = ReviewBuilder.create(22L, p2, c2)
				.rate(Rate.FAIR.score()).title("Not very long")
				.date(DateFormatUtils.formatDate("23/07/2017"))
				.build();
		
		Review r4 = ReviewBuilder.create(23L, p2, c3)
				.rate(Rate.POOR.score()).title("It's broken")
				.date(DateFormatUtils.formatDate("12/07/2017"))
				.build();
		reviews.add(r1);
		reviews.add(r2);
		reviews.add(r3);
		reviews.add(r4);
		
//		List<Review> rww = ;
		setReviewsForProducts(p1, p2);
//		p1.setReviews(reviews.stream().filter(r->r.getProduct().equals(p1)).collect(Collectors.toList()));
	}
	
	public static List<Customer> getCustomers(){
		return customers;
	}
	
	private static void setReviewsForProducts(Product... product) {
		for(Product p : product){
			p.setReviews(reviews.stream().filter(r->r.getProduct().equals(p)).collect(Collectors.toList()));
		}
	}

	public static List<Review> getReviews(){
		return reviews;
	}
	
	public static List<Product> getProducts() {
		return products;
	}
}
