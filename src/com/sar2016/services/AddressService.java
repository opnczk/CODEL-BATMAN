package com.sar2016.services;

import com.sar2016.dao.AddressDAO;

public class AddressService {
	private AddressDAO dao;
	
	public AddressService(){
		this.dao = new AddressDAO();
	}
	public void create(String placeId,String lat,String lng, String street, String city, String zip, String country) {
		dao.create(placeId, lat, lng, street, city, zip, country);
	}
	

}
