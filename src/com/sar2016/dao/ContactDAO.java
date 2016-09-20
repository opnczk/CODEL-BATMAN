package com.sar2016.dao;

import java.util.ArrayList;
import java.util.List;

import com.sar2016.entities.Contact;

public class ContactDAO {
	List<Contact> contacts = new ArrayList<Contact>();

	public void create(String firstName, String lastName, String nickName,
			String email) {
		System.out.println("On est arrivé au DAO");
		System.out.println("Contact : "+firstName+" "+lastName+" "+nickName+" "+email);
		this.contacts.add(new Contact(firstName, lastName, nickName, email));
	}

	public Contact findByMail(String email) {
		for(int i = 0; i < this.contacts.size(); i++)
		{
			if(this.contacts.get(i).getMail().equals(email))
				return this.contacts.get(i);
		}
		return null;
	}
	
}
