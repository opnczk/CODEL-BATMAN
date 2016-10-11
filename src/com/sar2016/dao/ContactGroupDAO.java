package com.sar2016.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sar2016.entities.ContactGroup;
import com.sar2016.util.HibernateUtil;

public class ContactGroupDAO {
	
	public void create(String name) {
		
		ContactGroup c = new ContactGroup(name);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.save(c);
		
		tx.commit();
		
	}
	
	public ContactGroup getById(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		return (ContactGroup)session.get(ContactGroup.class, id);
	}
	
	public void deleteById(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.delete(id);
	}
}
