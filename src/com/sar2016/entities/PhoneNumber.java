package com.sar2016.entities;

public class PhoneNumber {
	long id;
	String phoneKind, phoneNumber;
	Contact contact;
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
		if (!contact.getProfiles().contains(this)) {
			contact.addProfile(this);
		}	
	}

	public PhoneNumber() {
		
	}
	
	public PhoneNumber(String phoneKind, String phoneNumber, Contact contact) {
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
		this.contact = contact;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhoneKind() {
		return phoneKind;
	}

	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString()
	{
		return "MemRef :"+super.toString()+" - PhoneKind :"+this.phoneKind+" - Number :"+this.phoneNumber;
	}
}
