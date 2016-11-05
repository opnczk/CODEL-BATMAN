package com.sar2016.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.Address;
import com.sar2016.entities.Contact;
import com.sar2016.entities.Enterprise;
import com.sar2016.util.HibernateUtil;

public class EnterpriseDAO extends ContactDAO{
	
	public Contact create(String nom, String mail, int siret) {
		
		
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		
		Enterprise c = (Enterprise) ac.getBean("Entreprise");
		c.setFirstName(nom);
		c.setEmail(mail);
		c.setNumSiret(siret);
		
		((Session) getHibernateTemplate().getSessionFactory()).save(c);
		
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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		return (Enterprise)session.get(Enterprise.class, id);
	}
	
	public void deleteById(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.delete(id);
	}
	
	public List<Enterprise> getEnterprises(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction tx=session.beginTransaction();
		String query = "from Contact as t where t.class = Enterprise";
		System.out.println("BOOYAYAYA");
		
		List<Enterprise> rs = session.createQuery(query).list();
		session.close();
		
		return rs;
	}
}
