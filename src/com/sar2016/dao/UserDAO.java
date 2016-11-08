package com.sar2016.dao;

import org.springframework.transaction.annotation.Transactional;

import com.sar2016.entities.User;

public class UserDAO {
	@Transactional (readOnly = false)
	public void create(String firstName, String lastName, String email, String password) {
		//ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		//getHibernateTemplate().setCheckWriteOperations(false);

		//User c = (User)ac.getBean("User");
		//c.setFirstName(firstName);
		//c.setLastName(lastName);
		//c.setEmail(email);
		//c.setPassword(password);
		
		/*User c = new User(firstName, lastName, email, password);

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.save(c);
		
		tx.commit();*/
	}

	public boolean login(String email, String password) {
		/*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		String query = "from User as t where t.email = :mail and t.password = :pass";
		User rs = ((User) session.createQuery(query).setEntity("mail", email).setEntity("pass", password).uniqueResult());

		if(rs != null) 
			return true;
		else
			return false;*/
		String query = "from User as t where t.email = :mail and t.password = :pass";

		return false; //getHibernateTemplate().find(query, email, password);
	}

	public User getById(long id) {
		/*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		return (User)session.get(User.class, id);*/
		return null;//(User)getHibernateTemplate().get(User.class, id);
	}

}
