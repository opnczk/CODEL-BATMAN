package com.sar2016.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.Contact;
import com.sar2016.entities.Enterprise;

@Transactional
public class EnterpriseDAO extends HibernateDaoSupport {
	
	public Enterprise create(String nom, String mail, long siret) {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		getHibernateTemplate().setCheckWriteOperations(false);

		Enterprise c = (Enterprise) ac.getBean("Entreprise");
		c.setFirstName(nom);
		c.setEmail(mail);
		c.setNumSiret(siret);
		
		getHibernateTemplate().save(c);
		
		return c;
	}
	
	public Enterprise getById(long id) {

		return (Enterprise) getHibernateTemplate().get(Enterprise.class, id);
	}
	
	public void deleteById(long id){

		getHibernateTemplate().delete(id);
	}
	
	public List<Enterprise> getEnterprises(long id){

		String query = "from Contact as t where t.class = Enterprise and t.user.id = ?";

		List<Enterprise> rs = (List<Enterprise>) (getHibernateTemplate().find(query, id));
		return rs;
	}

	public Enterprise create(Enterprise c) {
		getHibernateTemplate().persist(c);
		getHibernateTemplate().save(c);
		return c;
	}
}
