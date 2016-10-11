package com.sar2016.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sar2016.entities.Address;
import com.sar2016.entities.Contact;
import com.sar2016.util.HibernateUtil;

public class ContactDAO {
	
	public void create(String firstName, String lastName, String nickName,
			String email, Address address) {
		
		Contact c = new Contact(firstName, lastName, nickName, email, address);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.save(c);
		
		tx.commit();
				
	}

	public Contact getById(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		return (Contact)session.get(Contact.class, id);
	}

	public Contact getByMail(String mail) {
		//Written in HQL
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		String query = "from Contact_Table as t where t.email = :mail";
		Contact rs = ((Contact) session.createQuery(query).setEntity("mail", mail).uniqueResult());

		return rs;
	}

	public List<Contact> searchByMail(String mail) {
		//Written in HQL
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		String query = "from Contact as t where t.email like :mail";
		List<Contact> rs = session.createQuery(query).setParameter("mail", "%"+mail+"%").list();

		return rs;
	}	
	
	public void deleteById(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.delete(id);

	}
}
