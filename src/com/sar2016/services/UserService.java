package com.sar2016.services;

import com.sar2016.dao.UserDAO;
import com.sar2016.entities.User;

public class UserService {
	private UserDAO dao;
	
	public UserService(){
		this.dao = new UserDAO();
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
}
