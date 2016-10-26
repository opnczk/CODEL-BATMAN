package com.sar2016.services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sar2016.dao.PhoneNumberDAO;
import com.sar2016.entities.Contact;
import com.sar2016.entities.PhoneNumber;

public class PhoneNumberService {
	private PhoneNumberDAO dao;
	
	public PhoneNumberService(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("file:/users/nfs/Etu2/3410322/Workspace/CODEL-BATMAN/WebContent/WEB-INF/applicationContext.xml");
		this.dao = (PhoneNumberDAO)ac.getBean("PhoneNumberDAO");
		//this.dao = new PhoneNumberDAO();
	}
	public PhoneNumber create(String kind, String number) {
		return dao.create(kind, number);
	}
}
