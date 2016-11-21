package com.sar2016.services;

import java.util.List;

import com.sar2016.dao.ContactDAO;
import com.sar2016.dao.ContactGroupDAO;
import com.sar2016.entities.Contact;
import com.sar2016.entities.ContactGroup;


public class ContactGroupService {
	private ContactGroupDAO dao;
	
	public ContactGroupService(){

	}

	public ContactGroupService(ContactGroupDAO dao){
		this.dao = dao;
	}
	
	public ContactGroup create(ContactGroup c ){
		return dao.create(c);
	}
	
	public ContactGroup create(String groupName) {
		return dao.create(groupName);
	}
	
	public ContactGroup getById(long id){
		return dao.getById(id);
	}

	public List<ContactGroup> getAll(long id) {
		return dao.getAll(id);
	}
	
	public void deleteById(long id) {
		dao.deleteById(id);
	}

	public void delete(ContactGroup c) {
		dao.delete(c);
	}
	
	public ContactGroupDAO getDao() {
		return dao;
	}

	public void setDao(ContactGroupDAO dao) {
		this.dao = dao;
	}
	
	public List<ContactGroup> getByPart(String str, Long user_id){
		return dao.searchByPart(str, user_id);
	}

	public void update(ContactGroup cg) {
		dao.updateContactGroup(cg);
	}
}
