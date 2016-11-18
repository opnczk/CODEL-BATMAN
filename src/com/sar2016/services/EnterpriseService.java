package com.sar2016.services;

import java.util.List;

import com.sar2016.dao.EnterpriseDAO;
import com.sar2016.entities.Contact;
import com.sar2016.entities.Enterprise;

public class EnterpriseService extends ContactService {
	private EnterpriseDAO dao;
	
	public EnterpriseService(){
		
	}

	public EnterpriseService(EnterpriseDAO dao){
		this.dao = dao;
	}
	
	public Contact create(String nom, String mail, int siret) {
		return dao.create(nom, mail, siret);
	}
	public List<Contact> getAll(long id) {
		return dao.getAll(id);
	}
	public List<Enterprise> getEnterprises(long id) {
		return dao.getEnterprises(id);
	}

	public EnterpriseDAO getDao() {
		return dao;
	}

	public void setDao(EnterpriseDAO dao) {
		this.dao = dao;
	}	
}
