package com.sar2016.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sar2016.entities.Address;
import com.sar2016.entities.Contact;
import com.sar2016.util.HibernateUtil;

public class ContactDAO {
	
	public Contact create(String firstName, String lastName, String nickName,
			String email) {
		
		Contact c = new Contact(firstName, lastName, nickName, email);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx = null;
		if(!session.getTransaction().isActive()){
			tx = session.beginTransaction();
		}else{
			tx = session.getTransaction();
		}
		
		session.save(c);
		
		tx.commit();
		
		return c;
	}

	public Contact getById(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		if(!session.getTransaction().isActive()){
			Transaction tx = session.beginTransaction();
		}
		
		return (Contact)session.get(Contact.class, id);
	}

	public Contact getByMail(String mail) {
		//Written in HQL
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		String query = "from Contact as t where t.email = :mail";
		Contact rs = ((Contact) session.createQuery(query).setEntity("mail", mail).uniqueResult());

		return rs;
	}

	public List<Contact> searchByMail(String mail) {
		//Written in HQL
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if(!session.getTransaction().isActive()){
			Transaction tx = session.beginTransaction();
		}
		String query = "from Contact as t where t.email like :mail";
		System.out.println("BOOYAYAYA");
		
		String str ="%" + mail + "%";
		System.out.println(str);
		System.out.println(str.length());
		List<Contact> rs = session.createQuery(query).setString("mail", str).list();
		System.out.println("---------------"+rs.size());
		session.close();
		
		return rs;
	}	
	
	public void deleteById(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		

		Transaction tx = null;
		if(!session.getTransaction().isActive()){
			tx = session.beginTransaction();
		}else{
			tx = session.getTransaction();
		}
		
		session.delete(this.getById(id));
		
		tx.commit();
	}

	public List<Contact> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if(!session.getTransaction().isActive()){
			Transaction tx = session.beginTransaction();
		}
		String query = "from Contact";
		System.out.println("BOOYAYAYA");
		
		List<Contact> rs = session.createQuery(query).list();
		session.close();
		
		return rs;
	}
	
	public List<Contact> getContacts(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if(!session.getTransaction().isActive()){
			Transaction tx = session.beginTransaction();
		}
		String query = "from Contact as t where t.class = Contact";
		System.out.println("BOOYAYAYA");
		
		List<Contact> rs = session.createQuery(query).list();
		session.close();
		
		return rs;
	}
}
