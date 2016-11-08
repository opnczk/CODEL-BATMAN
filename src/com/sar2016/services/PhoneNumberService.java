package com.sar2016.services;

import com.sar2016.dao.PhoneNumberDAO;
import com.sar2016.entities.PhoneNumber;

public class PhoneNumberService {
	private PhoneNumberDAO dao;
	
	public PhoneNumberService(){

	}

	public PhoneNumberService(PhoneNumberDAO dao){
		this.dao = dao;
	}
	
	public PhoneNumber create(String kind, String number) {
		return dao.create(kind, number);
	}

	public PhoneNumberDAO getDao() {
		return dao;
	}

	public void setDao(PhoneNumberDAO dao) {
		this.dao = dao;
	}
}
