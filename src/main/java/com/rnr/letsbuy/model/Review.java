package com.rnr.letsbuy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "REVIEW")
@Table(name = "REVIEW")
public class Review extends BaseModel<Long> {

	@ManyToOne
	@NotNull
	@JsonManagedReference
	private Product product;
	@ManyToOne
	@NotNull
	@JsonManagedReference
	private Customer customer;
	private String title;
	/*
	 * By default, stringed properties will be mapped as VARCHAR(255). We make
	 * this property/column as a CLOB in H2.
	 */
	@Column(columnDefinition = "TEXT")
	private String body;

	private int rate;

	@CreatedDate
	@LastModifiedDate
	@Column(columnDefinition = "TIMESTAMP", name = "DATETIME")
	private Date date;

	public enum Rate {
		POOR(1), BAD(2), FAIR(3), GOOD(4), EXCELLENT(5);
		private int score;

		private Rate(int score) {
			this.score = score;
		}

		public int score() {
			return score;
		}
	}

	public Review() {
		super();
	}

	public Review(Long id, Product product, Customer customer) {
		super();
		this.id = id;
		this.product = product;
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", product=" + product + ", customer=" + customer + ", date=" + date + "]";
	}

	public static class ReviewBuilder {
		private Long id;
		private Product product;
		private Customer customer;
		private String title;
		private String body;
		private Date date;
		private int rate;

		public static ReviewBuilder create() {
			return new ReviewBuilder();
		}

		public static ReviewBuilder create(Long id, Product product, Customer customer) {
			ReviewBuilder reviewBuilder = new ReviewBuilder();
			reviewBuilder.id(id);
			reviewBuilder.product(product);
			reviewBuilder.customer(customer);
			return reviewBuilder;
		}

		private ReviewBuilder() {
			super();
		}

		public ReviewBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public ReviewBuilder product(Product product) {
			this.product = product;
			return this;
		}

		public ReviewBuilder customer(Customer customer) {
			this.customer = customer;
			return this;
		}

		public ReviewBuilder title(String title) {
			this.title = title;
			return this;
		}

		public ReviewBuilder body(String body) {
			this.body = body;
			return this;
		}

		public ReviewBuilder rate(int rate) {
			this.rate = rate;
			return this;
		}

		public ReviewBuilder date(Date date) {
			this.date = date;
			return this;
		}

		public Review build() {
			Review review = new Review(id, product, customer);
			review.setTitle(title);
			review.setBody(body);
			review.setDate(date);
			review.setRate(rate);
			return review;
		}
	}

}
