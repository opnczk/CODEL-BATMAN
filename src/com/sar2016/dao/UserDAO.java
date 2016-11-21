package com.sar2016.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.User;

@Transactional
public class UserDAO extends HibernateDaoSupport {
	
	
	public User create(String firstName, String lastName, String email, String password) {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();

		User u = (User)ac.getBean("User");
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setEmail(email);
		u.setPassword(password);

		getHibernateTemplate().persist(u);
		getHibernateTemplate().save(u);
		return u;
	}
	
	public User create(User u) {
		getHibernateTemplate().persist(u);
		long id=(long) getHibernateTemplate().save(u);
		u=(User) getHibernateTemplate().get(User.class, id);
		return u;
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

	public User loginGoogle(String token, String email) {
		String query = "from User as t where t.email = ? ";
		List<?> user = getHibernateTemplate().find(query, email);
		if(user.size() > 0)
			return (User) user.get(0);
		else
			return null;
		}

}
