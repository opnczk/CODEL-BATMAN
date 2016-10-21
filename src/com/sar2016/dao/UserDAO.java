package com.sar2016.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sar2016.entities.Contact;
import com.sar2016.entities.User;
import com.sar2016.util.HibernateUtil;

public class UserDAO {

	public void create(String firstName, String lastName, String email,
			String password) {
		User c = new User(firstName, lastName, email, password);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.save(c);
		
		tx.commit();
	}

	public boolean login(String email, String password) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		String query = "from User as t where t.email = :mail and t.password = :pass";
		User rs = ((User) session.createQuery(query).setEntity("mail", email).setEntity("pass", password).uniqueResult());

		if(rs != null) 
			return true;
		else
			return false;
	}

	public User getById(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		return (User)session.get(User.class, id);
	}

}
