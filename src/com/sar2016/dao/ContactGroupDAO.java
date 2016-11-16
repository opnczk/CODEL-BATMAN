package com.sar2016.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.ContactGroup;

@Transactional
public class ContactGroupDAO extends HibernateDaoSupport{
	
	public void create(String name) {
		
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		getHibernateTemplate().setCheckWriteOperations(false);

		ContactGroup c = (ContactGroup) ac.getBean("ContactGroup");
		c.setGroupName(name);
		
		getHibernateTemplate().save(c);
	}
	
	public ContactGroup getById(long id) {

		return getHibernateTemplate().get(ContactGroup.class, id);
	}

	public void deleteById(long id){

		getHibernateTemplate().delete(id);
	}
}
