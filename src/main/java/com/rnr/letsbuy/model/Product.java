package com.rnr.letsbuy.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "PRODUCT")
@Table(name = "PRODUCT")
public class Product extends BaseModel<Long> {
	
	@NotBlank
	@Size(min=2, max=64)
	private String name;
	
	@NotNull
	@DecimalMin("0.01")
	@DecimalMax("9999999.99")
	@Digits(integer=7, fraction=2)
	private BigDecimal price;
	@OneToMany(mappedBy = "product")
	/*
	 * http://keenformatics.blogspot.com/2013/08/how-to-solve-json-infinite-
	 * recursion.html
	 * http://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-
	 * recursion
	 * 
	 * Jackson and Json serialization. As the docs state, bi-directional
	 * references for ORM-managed beans (iBatis, Hibernate) would cause
	 * serialization to failure since they are cyclic dependencies. Since
	 * Jackson 1.6, this problem has been solved by the introduction of two new
	 * annotations: @JsonManagedReference and @JsonBackReference (and see the
	 * end of this post to give a look at the @JsonIdentityInfo annotation).
	 * WARN: This may be the cause of the problem.
	 */
	@JsonBackReference
	private List<Review> reviews;

	/**
	 * The relation between a product and tagProduct should actually be
	 * ManyToMany.
	 */
	@ElementCollection
	@CollectionTable(name = "PRODUCT_TAGS", joinColumns = @JoinColumn(name = "PRODUCT_ID"))
	@Column(name = "LABEL")
	private Set<String> tags;
	
	@Column(name="NO_ORDERS")
	private Long soldCounter;

	public Product() {
		super();
	}

	public Product(Long id, String name, BigDecimal price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Long getSoldCounter() {
		return soldCounter;
	}

	public void setSoldCounter(Long soldCounter) {
		this.soldCounter = soldCounter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

}
