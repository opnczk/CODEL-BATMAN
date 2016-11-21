package com.sar2016.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.PhoneNumber;

@Transactional
public class PhoneNumberDAO extends HibernateDaoSupport {
	
	public PhoneNumber create(String phoneKind, String phoneNumber) {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();

		PhoneNumber pn = (PhoneNumber) ac.getBean("PhoneNumber");
		pn.setPhoneKind(phoneKind);
		pn.setPhoneNumber(phoneNumber);
		getHibernateTemplate().persist(pn);
		getHibernateTemplate().save(pn);

		return pn;
	}
	
	public PhoneNumber create(PhoneNumber pn){
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

	public List<PhoneNumber> searchByPart(String str, Long user_id) {
		//Written in HQL
		String query = "from PhoneNumber as t where t.phoneNumber = ? or t.phoneKind = ? and t.user.id = ?";		
		String param ="%"+str+"%";
	
		List<PhoneNumber> rs = ((List<PhoneNumber>) getHibernateTemplate().find(query, "%"+param+"%", "%"+param+"%", user_id));
		return rs;
	}
}
