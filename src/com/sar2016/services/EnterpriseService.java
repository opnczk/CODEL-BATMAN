package com.sar2016.services;

import java.util.List;

import com.sar2016.dao.EnterpriseDAO;
import com.sar2016.entities.Contact;
import com.sar2016.entities.Enterprise;

public class EnterpriseService extends Enterprise {
	private EnterpriseDAO dao;
	
	public EnterpriseService(){
		this.dao = new EnterpriseDAO();
	}
	public void create(String nom, String mail, String siret) {
		dao.create(nom, mail, siret);
	}
	public List<Contact> getAll() {
		return dao.getAll();
	}
	public List<Enterprise> getEnterprises() {
		return dao.getEnterprises();
	}

}
