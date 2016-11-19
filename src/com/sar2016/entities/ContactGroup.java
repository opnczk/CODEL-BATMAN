package com.sar2016.entities;

import java.util.HashSet;
import java.util.Set;

public class ContactGroup {
	long id;
	private long version;
	String groupName;
	private Set<Contact> contacts = new HashSet<Contact>();
	private User user;
	
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

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
