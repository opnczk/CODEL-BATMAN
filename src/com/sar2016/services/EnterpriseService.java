package com.sar2016.services;

import com.sar2016.dao.ContactDAO;
import com.sar2016.dao.EnterpriseDAO;
import com.sar2016.entities.Enterprise;

public class EnterpriseService extends Enterprise {
	private EnterpriseDAO dao;
	
	public EnterpriseService(){
		this.dao = new EnterpriseDAO();
	}
	public void create(String nom, String mail, String siret) {
		dao.create(nom, mail, siret);
	}

}
