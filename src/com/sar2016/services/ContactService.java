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
	
	public Contact create(String firstName, String lastName, String nickName,
			String email) {
		
		System.out.println("On est arrivé au Service.");
		
		return dao.create(firstName, lastName, nickName, email);
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

	public Contact getContact(long id) {
		return dao.getById(id);
	}

	public List<Contact> getAll() {
		return dao.getAll();
	}

	public List<Contact> getContacts() {
		return dao.getContacts(); 
	}

	public void deleteById(long id) {
		dao.deleteById(id);
	}
}
