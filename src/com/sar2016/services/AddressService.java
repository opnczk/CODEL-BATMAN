package com.sar2016.services;

import com.sar2016.dao.AddressDAO;
import com.sar2016.entities.Address;

public class AddressService {
	private AddressDAO dao;
	
	public AddressService(){
		this.dao = new AddressDAO();
	}
	public Address create(String placeId,String lat,String lng, String street, String city, String zip, String country) {
		return dao.create(placeId, lat, lng, street, city, zip, country);
	}
	

}
