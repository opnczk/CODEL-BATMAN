package com.sar2016.entities;

import java.util.Set;

public class ContactGroup {
	long id;
	String groupName;
	private Set<Contact> contacts;

	public ContactGroup() {
		
	}
	
	public ContactGroup(String groupName) {
		this.groupName = groupName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public Set<Contact> getContacts(){
		return this.contacts;
	}
	
	public void setContacts(Set<Contact> contacts){
		this.contacts = contacts;
	}

	public void addContact(Contact contact) {
		this.contacts.add(contact);
		if(!contact.getBooks().contains(this))
			contact.addBook(this);
	}

	public void removeContact(Contact contact) {
		this.contacts.remove(contact);
		if(contact.getBooks().contains(this))
			contact.removeBook(this);
	}
	
	
}
