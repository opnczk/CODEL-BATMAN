package com.sar2016.entities;

import java.util.Set;

public class ContactGroup {
	long id;
	String groupName;

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
		return null;
	}
	
	public void setContacts(Set<Contact> contacts){
		
	}
}
