package com.sar2016.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sar2016.entities.Address;
import com.sar2016.util.HibernateUtil;

public class AddressDAO {
	
	public void create(String street, String city, String zip, String country) {
		
		Address c = new Address(street, city, zip, country);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.save(c);
		
		tx.commit();
		
	}

	public Address getById(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx= session.beginTransaction();
		
		return (Address)session.get(Address.class, id);
	}
	
	public void deleteById(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.delete(id);
	}
}
