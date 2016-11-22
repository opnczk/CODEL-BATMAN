package com.sar2016.entities;

public class PhoneNumber {
	long id;
	private int version;
	String phoneKind, phoneNumber;
	Contact contact;
	
	public PhoneNumber() {
		
	}
	
	public PhoneNumber(String phoneKind, String phoneNumber) {
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
	}	
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;	
		if (!contact.getProfiles().contains(this))
			contact.addProfile(this);
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
