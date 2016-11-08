package com.sar2016.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.Contact;
import com.sar2016.entities.Enterprise;

public class EnterpriseDAO extends ContactDAO{
	@Transactional (readOnly = false)
	public Contact create(String nom, String mail, int siret) {
		
		
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		getHibernateTemplate().setCheckWriteOperations(false);

		Enterprise c = (Enterprise) ac.getBean("Entreprise");
		c.setFirstName(nom);
		c.setEmail(mail);
		c.setNumSiret(siret);
		
		getHibernateTemplate().save(c);
		
		//Enterprise e = new Enterprise(nom, mail, siret);
		/*
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.save(e);
		
		tx.commit();
		*/
		return c;
	}
	
	public Enterprise getById(long id) {
		/*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		return (Enterprise)session.get(Enterprise.class, id);*/
		return (Enterprise) getHibernateTemplate().get(Enterprise.class, id);
	}
	
	public void deleteById(long id){
		/*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.delete(id);*/
		getHibernateTemplate().delete(id);
	}
	
	public List<Enterprise> getEnterprises(){
		/*Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction tx=session.beginTransaction();*/
		String query = "from Contact as t where t.class = Enterprise";
		
		//List<Enterprise> rs = session.createQuery(query).list();
		//session.close();
		List<Enterprise> rs = (List<Enterprise>) (getHibernateTemplate().find(query));
		return rs;
	}
}
