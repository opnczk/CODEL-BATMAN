package com.sar2016.services;

import java.util.List;

import com.sar2016.dao.ContactDAO;
import com.sar2016.entities.Address;
import com.sar2016.entities.Contact;

public class ContactService {
	private ContactDAO dao;
	
	public ContactService(){
		this.dao = new ContactDAO();
	}
	
	public void create(String firstName, String lastName, String nickName,
			String email, Address address) {
		
		System.out.println("On est arrivé au Service.");
		
		dao.create(firstName, lastName, nickName, email, address);
	}
	
	public Contact getById(long id){
		return dao.getById(id);
	}
	
	public Contact getByMail(String mail){
		return dao.getByMail(mail);
	}
	
	public List<Contact> searchByMail(String mail){
		return dao.searchByMail(mail);
	}
}
