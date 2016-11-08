package com.sar2016.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.ContactGroup;

public class ContactGroupDAO extends HibernateDaoSupport{
	@Transactional (readOnly = false)
	public void create(String name) {
		
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		getHibernateTemplate().setCheckWriteOperations(false);

		ContactGroup c = (ContactGroup) ac.getBean("ContactGroup");
		c.setGroupName(name);
		
		getHibernateTemplate().save(c);
		
		//ContactGroup c = new ContactGroup(name);
		/*
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.save(c);
		
		tx.commit();
		*/
	}
	
	public ContactGroup getById(long id) {
		/*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		return (ContactGroup)session.get(ContactGroup.class, id);*/
		return getHibernateTemplate().get(ContactGroup.class, id);
	}
	@Transactional (readOnly = false)
	public void deleteById(long id){
		/*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.delete(id);*/
		getHibernateTemplate().delete(id);
	}
}
