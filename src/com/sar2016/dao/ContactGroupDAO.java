package com.sar2016.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.Address;
import com.sar2016.entities.ContactGroup;
import com.sar2016.util.HibernateUtil;

public class ContactGroupDAO extends HibernateDaoSupport{
	
	public void create(String name) {
		
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		
		ContactGroup c = (ContactGroup) ac.getBean("ContactGroup");
		c.setGroupName(name);
		
		((Session) getHibernateTemplate().getSessionFactory()).save(c);
		
		//ContactGroup c = new ContactGroup(name);
		/*
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.save(c);
		
		tx.commit();
		*/
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
