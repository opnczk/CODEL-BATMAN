package com.sar2016.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sar2016.entities.Address;
import com.sar2016.entities.Contact;
import com.sar2016.entities.PhoneNumber;
import com.sar2016.util.HibernateUtil;

public class PhoneNumberDAO {
	
	public void create(String phoneKind, String phoneNumber) {
		
		PhoneNumber c = new PhoneNumber(phoneKind, phoneNumber);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.save(c);
		
		tx.commit();
		
	}
	
	public PhoneNumber getById(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		return (PhoneNumber)session.get(PhoneNumber.class, id);
	}
	
	public void deleteById(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.delete(id);
	}
}
