package com.sar2016.services;

import java.util.List;

import com.sar2016.dao.ContactDAO;
import com.sar2016.entities.Contact;
import com.sar2016.entities.User;


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
	
	public Contact create(String firstName, String lastName, String nickName, String email, User user) {
		return dao.create(firstName, lastName, nickName, email, user);
	}
	
	public Contact getById(long id){
		return dao.getById(id);
	}
	
	public List<Contact> getByPart(String str){
		return dao.searchByPart(str);
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

	public List<Contact> getAll(long id) {
		return dao.getAll(id);
	}

	public List<Contact> getContacts(long id) {
		return dao.getContacts(id); 
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
