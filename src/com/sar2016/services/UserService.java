package com.sar2016.services;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.sar2016.dao.UserDAO;
import com.sar2016.entities.User;

public class UserService extends HibernateDaoSupport{
	private UserDAO dao;
	
	public UserService(){
	}
	
	public UserService(UserDAO dao){
		this.dao = dao;
	}
	
	public void create(String firstName, String lastName,
			String email,String password) {
		dao.create(firstName, lastName, email, password);
	}
	
	public User getById(long id){
		return dao.getById(id);
	}
	
	public boolean login(String email,String password){
		return dao.login(email, password);
	}

	public UserDAO getDao() {
		return dao;
	}

	public void setDao(UserDAO dao) {
		this.dao = dao;
	}
}
