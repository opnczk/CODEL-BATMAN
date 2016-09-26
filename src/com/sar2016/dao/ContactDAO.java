package com.sar2016.dao;

import java.util.ArrayList;
import java.util.List;

import com.sar2016.entities.Contact;
import com.sar2016.helper.Helper;

public class ContactDAO {
	private List<Contact> contacts;
	
	public ContactDAO()
	{
		this.contacts = Helper.getPersistanceLayer();
	}
	
	public void create(String firstName, String lastName, String nickName,
			String email) {
		System.out.println("On est arrivé au DAO");
		this.contacts.add(new Contact(firstName, lastName, nickName, email));
	}

	public Contact findByMail(String email) {
		for(int i = 0; i < this.contacts.size(); i++)
		{	
			if(this.contacts.get(i).getMail().equals(email)){
				System.out.println(this.contacts.get(i));
				return this.contacts.get(i);
			}	
		}
		return null;
	}
	
}
