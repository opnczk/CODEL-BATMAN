package com.sar2016.services;

import com.sar2016.dao.ContactDAO;

public class ContactService {
	
	
	public void create(String firstName, String lastName, String nickName,
			String email) {
		
		System.out.println("On est arrivé au Service.");
		
		ContactDAO dao = new ContactDAO();
		dao.create(firstName, lastName, nickName, email);
	}
}
