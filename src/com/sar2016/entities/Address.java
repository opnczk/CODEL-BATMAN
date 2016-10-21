package com.sar2016.entities;

public class Address {
	long id;
	String street, city, zip, placeId, country;
	
	public Address() {
		
	}
	
	public Address(String street, String city, String zip, String country) {
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.country = country;
	}
	
	public Address(Address a) {
		// TODO DELETE this constr, as it is a constr for cloning.
		this.country = a.country;
		this.city = a.city;
		this.street = a.street;
		this.zip = a.zip;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPlaceId(){
		return this.placeId;
	}
	
	public void setPlaceId(String placeId){
		this.placeId = placeId;
	} 
	
	@Override
	public String toString()
	{
		return "MemRef :"+super.toString()+" - Address :"+this.street+" - Number :"+this.city;
	}
}
