package com.rnr.letsbuy.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address extends BaseModel<Long>{
	
	private String street;
	private int number;
	private int zipCode;
	private String city;
	
	public Address() {
	}
	
	public Address(String street, int number, int zipCode, String city) {
		super();
		this.street = street;
		this.number = number;
		this.zipCode = zipCode;
		this.city = city;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", number=" + number + ", zipCode=" + zipCode + ", city=" + city
				+ ", toString()=" + super.toString() + "]";
	}

}
