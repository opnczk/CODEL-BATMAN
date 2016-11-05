package com.sar2016.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.Address;
import com.sar2016.entities.Contact;
import com.sar2016.entities.PhoneNumber;
import com.sar2016.util.HibernateUtil;

public class PhoneNumberDAO extends HibernateDaoSupport {
	
	public PhoneNumber create(String phoneKind, String phoneNumber) {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		
		PhoneNumber c = (PhoneNumber) ac.getBean("PhoneNumber");
		c.setPhoneKind(phoneKind);
		c.setPhoneNumber(phoneNumber);
		
		((Session) getHibernateTemplate().getSessionFactory()).save(c);
		//PhoneNumber c = new PhoneNumber(phoneKind, phoneNumber);
		/*
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.save(c);
		
		tx.commit();
		*/
		return c;
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
