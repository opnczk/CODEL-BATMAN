package com.sar2016.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sar2016.entities.Contact;
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
	
	public List<Enterprise> getEnterprises(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction tx=session.beginTransaction();
		String query = "from Contact as t where t.class = Enterprise";
		System.out.println("BOOYAYAYA");
		
		List<Enterprise> rs = session.createQuery(query).list();
		session.close();
		
		return rs;
	}
}
