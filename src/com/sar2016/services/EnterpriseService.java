package com.sar2016.services;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;

import com.sar2016.dao.EnterpriseDAO;
import com.sar2016.entities.Contact;
import com.sar2016.entities.Enterprise;

public class EnterpriseService extends Enterprise {
	private EnterpriseDAO dao;
	
	public EnterpriseService(){
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		this.dao = (EnterpriseDAO)ac.getBean("EnterpriseDAO");
;		//this.dao = new EnterpriseDAO();
	}
	public Contact create(String nom, String mail, int siret) {
		return dao.create(nom, mail, siret);
	}
	public List<Contact> getAll() {
		return dao.getAll();
	}
	public List<Enterprise> getEnterprises() {
		return dao.getEnterprises();
	}

}
