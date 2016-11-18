package com.sar2016.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.sar2016.entities.User;

@Transactional
public class UserDAO extends HibernateDaoSupport {
	
	
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

	public User login(String email, String password) {
		String query = "from User as t where t.email = ? and t.password = ?";
		
	    String[] parameterArray = new String[] { email,password}; 
		List<?> user = getHibernateTemplate().find(query, parameterArray);
		
		if(user.size() > 0)
			return (User) user.get(0);
		else
			return null;
	}

	public User getById(long id) {
		String query = "from User as t where t.id =  ? ";
		
		List<?> user = getHibernateTemplate().find(query, id);
		
		if(user.size() > 0)
			return (User) user.get(0);
		else
			return null;
	}

	public User create(User u) {
		getHibernateTemplate().persist(u);
		getHibernateTemplate().save(u);
		return u;
	}

}
