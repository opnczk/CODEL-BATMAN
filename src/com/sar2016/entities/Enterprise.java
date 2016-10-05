package com.sar2016.entities;

public class Enterprise extends Contact{
	long id;
	int numSiret;
	
	public Enterprise() {
		
	}
	
	public Enterprise ( String firstName, String lastName, String nickName, String email, Address address, int numSiret)
	{
		this.numSiret = numSiret;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.address = address;
		this.email = email;
		System.out.println(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(int numSiret) {
		this.numSiret = numSiret;
	}

}
