package com.rnr.letsbuy.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "CUSTOMER")
@Table(name = "CUSTOMER")
public class Customer extends BaseModel<Long> {

	@NotNull
	private String name;
	@NotNull
	private String email;
	@NotNull
	private Date registered;

	public Customer() {
		super();
	}

	public Customer(Long id, String name, String email, Date registered) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.registered = registered;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", registered=" + registered + "]";
	}
}
