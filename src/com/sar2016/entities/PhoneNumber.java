package com.sar2016.entities;

public class PhoneNumber {
	long id;
	String phoneKind, phoneNumber;
	
	public PhoneNumber() {
		
	}
	
	public PhoneNumber(String phoneKind, String phoneNumber) {
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
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
