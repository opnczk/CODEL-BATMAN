package com.sar2016.services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sar2016.dao.AddressDAO;
import com.sar2016.entities.Address;

public class AddressService {
	private AddressDAO dao;
	
	public AddressService(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("file:/users/nfs/Etu2/3410322/Workspace/CODEL-BATMAN/WebContent/WEB-INF/applicationContext.xml");
		this.dao = (AddressDAO) ac.getBean("AddressDAO");
		//this.dao = new AddressDAO();
	}
	public Address create(String placeId,String lat,String lng, String street, String city, String zip, String country) {
		return dao.create(placeId, lat, lng, street, city, zip, country);
	}
	

}
