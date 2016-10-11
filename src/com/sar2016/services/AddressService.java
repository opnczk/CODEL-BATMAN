package com.sar2016.services;

import com.sar2016.dao.AddressDAO;

public class AddressService {
	private AddressDAO dao;
	
	public AddressService(){
		this.dao = new AddressDAO();
	}
	public void create(String street, String city, String zip, int country) {
		dao.create(street, city, zip, country);
	}
	

}
