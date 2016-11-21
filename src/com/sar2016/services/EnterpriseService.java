package com.sar2016.services;

import java.util.List;

import com.sar2016.dao.EnterpriseDAO;
import com.sar2016.entities.Contact;
import com.sar2016.entities.Enterprise;

public class EnterpriseService {
	private EnterpriseDAO dao;
	
	public EnterpriseService(){
		
	}

	public EnterpriseService(EnterpriseDAO dao){
		this.dao = dao;
	}
	
	public Contact create(String nom, String mail, long siret) {
		return dao.create(nom, mail, siret);
	}
	
	public Enterprise create(Enterprise e ){
		return dao.create(e);
	}
	
	public List<Enterprise> getEnterprises(long user_id) {
		return dao.getEnterprises(user_id);
	}

	public EnterpriseDAO getDao() {
		return dao;
	}

	public void setDao(EnterpriseDAO dao) {
		this.dao = dao;
	}

	public void update(Enterprise e) {
		this.dao.update(e);
	}	
}
