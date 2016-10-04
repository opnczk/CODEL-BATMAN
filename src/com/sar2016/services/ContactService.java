package com.sar2016.services;

import com.sar2016.dao.ContactDAO;
import com.sar2016.entities.Address;

public class ContactService {
	
	
	public void create(String firstName, String lastName, String nickName,
			String email, Address address) {
		
		System.out.println("On est arrivé au Service.");
		
		ContactDAO dao = new ContactDAO();
		dao.create(firstName, lastName, nickName, email, address);
	}
}
