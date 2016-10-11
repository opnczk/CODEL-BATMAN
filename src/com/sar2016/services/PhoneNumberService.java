package com.sar2016.services;

import com.sar2016.dao.PhoneNumberDAO;
import com.sar2016.entities.Contact;

public class PhoneNumberService {
	private PhoneNumberDAO dao;
	
	public PhoneNumberService(){
		this.dao = new PhoneNumberDAO();
	}
	public void create(String kind, String number) {
		dao.create(kind, number);
	}
}
