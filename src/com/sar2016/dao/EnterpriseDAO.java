package com.sar2016.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sar2016.entities.Address;
import com.sar2016.entities.Enterprise;
import com.sar2016.util.HibernateUtil;

public class EnterpriseDAO extends ContactDAO{
	
	public void create(String nom, String mail,
			String siret) {
		Enterprise e = new Enterprise(nom, mail, siret);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.save(e);
		
		tx.commit();
		
	}
	
	public Enterprise getById(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		return (Enterprise)session.get(Enterprise.class, id);
	}
	
	public void deleteById(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.delete(id);
	}
}
