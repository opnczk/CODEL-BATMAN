package com.sar2016.services;

import com.sar2016.dao.PhoneNumberDAO;
import com.sar2016.entities.Contact;
import com.sar2016.entities.PhoneNumber;

public class PhoneNumberService {
	private PhoneNumberDAO dao;
	
	public PhoneNumberService(){
		this.dao = new PhoneNumberDAO();
	}
	public PhoneNumber create(String kind, String number) {
		return dao.create(kind, number);
	}
}
