package com.sar2016.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.PhoneNumber;

public class PhoneNumberDAO extends HibernateDaoSupport {
	@Transactional (readOnly = false)
	public PhoneNumber create(String phoneKind, String phoneNumber) {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		getHibernateTemplate().setCheckWriteOperations(false);

		PhoneNumber c = (PhoneNumber) ac.getBean("PhoneNumber");
		c.setPhoneKind(phoneKind);
		c.setPhoneNumber(phoneNumber);
		
		getHibernateTemplate().save(c);
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
		/*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		return (PhoneNumber)session.get(PhoneNumber.class, id);*/
		return getHibernateTemplate().get(PhoneNumber.class, id);
	}
	@Transactional (readOnly = false)
	public void deleteById(long id){
		/*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.delete(id);*/
		getHibernateTemplate().delete(id);
	}
}
