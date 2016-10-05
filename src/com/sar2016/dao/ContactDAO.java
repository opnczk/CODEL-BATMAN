package com.sar2016.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sar2016.entities.Address;
import com.sar2016.entities.Contact;
import com.sar2016.entities.ContactGroup;
import com.sar2016.entities.Enterprise;
import com.sar2016.entities.PhoneNumber;
import com.sar2016.util.HibernateUtil;

public class ContactDAO {
	
	public void create(String firstName, String lastName, String nickName,
			String email, Address address) {
		System.out.println("On est arrivé au DAO");
		
		Address a = new Address("Place Jussieu","Paris", "75005", 33);
		Contact c = new Contact(firstName, lastName, nickName, email, a);
		PhoneNumber p = new PhoneNumber("Dom","06605893545", c);
		
		//Taille max d'un int 9 dec
		Enterprise e = new Enterprise(firstName, lastName, nickName, email+".entr", a,355464601);
		ContactGroup g = new ContactGroup("Groupe Famille");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.save(c);
		session.save(p);
		session.save(a);
		session.save(e);
		session.save(g);
		
		tx.commit();
		
	}

	public Contact getById(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		return (Contact)session.get(Contact.class, id);
	}

	public Contact getByMail(String mail) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contact> searchByMail(String mail) {
		// TODO Auto-generated method stub
		return null;
	}	
}
