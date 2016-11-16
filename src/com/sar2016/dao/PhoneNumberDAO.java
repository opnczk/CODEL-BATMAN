package com.sar2016.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.PhoneNumber;

@Transactional
public class PhoneNumberDAO extends HibernateDaoSupport {
	public PhoneNumber create(String phoneKind, String phoneNumber) {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		getHibernateTemplate().setCheckWriteOperations(false);

		PhoneNumber pn = (PhoneNumber) ac.getBean("PhoneNumber");
		pn.setPhoneKind(phoneKind);
		pn.setPhoneNumber(phoneNumber);
		getHibernateTemplate().persist(pn);
		getHibernateTemplate().save(pn);

		return pn;
	}
	
	public PhoneNumber getById(long id) {

		return getHibernateTemplate().get(PhoneNumber.class, id);
	}
	
	public void deleteById(long id){

		getHibernateTemplate().delete(id);
	}
}
