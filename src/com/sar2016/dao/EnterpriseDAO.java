package com.sar2016.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.Enterprise;

@Transactional
public class EnterpriseDAO extends HibernateDaoSupport {
	
	public Enterprise create(String nom, String mail, long siret) {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();

		Enterprise e = (Enterprise) ac.getBean("Enterprise");
		e.setFirstName(nom);
		e.setEmail(mail);
		e.setNumSiret(siret);
		
		getHibernateTemplate().persist(e);
		getHibernateTemplate().save(e);
		
		return e;
	}
	
	public Enterprise create(Enterprise e) {
		getHibernateTemplate().persist(e);
		getHibernateTemplate().save(e);
		return e;
	}
	
	public Enterprise getById(long id) {

		return (Enterprise) getHibernateTemplate().get(Enterprise.class, id);
	}
	
	public void deleteById(long id){

		getHibernateTemplate().delete(id);
	}
	
	public List<Enterprise> getEnterprises(long user_id){

		String query = "from Contact as t where t.class = Enterprise and t.user.id = ?";

		List<Enterprise> rs = (List<Enterprise>) (getHibernateTemplate().find(query, user_id));
		return rs;
	}

	public void update(Enterprise e) {
		getHibernateTemplate().merge(e);
		//getHibernateTemplate().update(e);
	}
}
