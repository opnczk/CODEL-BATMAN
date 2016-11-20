package com.sar2016.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.ContactGroup;

@Transactional
public class ContactGroupDAO extends HibernateDaoSupport {
	
	public ContactGroup create(ContactGroup cg) {
		
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		getHibernateTemplate().persist(cg);
		getHibernateTemplate().save(cg);
		return cg;
	}
	
	public ContactGroup getById(long id) {

		return getHibernateTemplate().get(ContactGroup.class, id);
	}

	public void deleteById(long id){

		getHibernateTemplate().delete(id);
	}

	public ContactGroup create(String c2) {
		
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();

		ContactGroup c = (ContactGroup) ac.getBean("ContactGroup");
		c.setGroupName(c2);
		getHibernateTemplate().persist(c2);
		getHibernateTemplate().save(c2);
		return c;
	}

	public List<ContactGroup> getAll(long id) {
		String query = "from ContactGroup as t where t.user.id = ?";
		List<ContactGroup> rs = (List<ContactGroup>) getHibernateTemplate().find(query, id);
		return (List<ContactGroup>) rs;
	}

	public void delete(ContactGroup c) {
		getHibernateTemplate().delete(c);
	}

	public List<ContactGroup> searchByPart(String str) {
		//Written in HQL
		String query = "from ContactGroup as t where t.groupName = ?";		
		String param ="%"+str+"%";
	
		List<ContactGroup> rs = ((List<ContactGroup>) getHibernateTemplate().find(query, "%"+param+"%"));
		return rs;
	}

	public void updateContactGroup(ContactGroup cg) {
		getHibernateTemplate().merge(cg); 
		getHibernateTemplate().update(cg);
	}
	
}
