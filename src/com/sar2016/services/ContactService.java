package com.sar2016.services;

import java.util.List;

import com.sar2016.dao.ContactDAO;
import com.sar2016.entities.Contact;


public class ContactService {
	private ContactDAO dao;
	
	public ContactService(){

	}

	public ContactService(ContactDAO dao){
		this.dao = dao;
	}
	
	public Contact create(Contact c ){
		return dao.create(c);
	}
	
	public Contact create(String firstName, String lastName, String nickName, String email) {
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

	public void delete(Contact c) {
		dao.delete(c);
	}
	
	public ContactDAO getDao() {
		return dao;
	}

	public void setDao(ContactDAO dao) {
		this.dao = dao;
	}
}
