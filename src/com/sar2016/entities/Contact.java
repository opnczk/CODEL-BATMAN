package com.sar2016.entities;

public class Contact {
	String firstName, lastName, nickName, email;
	
	public Contact ( String firstName, String lastName, String nickName, String email)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.email = email;
		System.out.println(this);
	}
	
	public String getMail()
	{
		return this.email;
	}
	
	@Override
	public String toString()
	{
		return "MemRef :"+super.toString()+" - FName :"+this.firstName+" - LName :"+this.lastName;
	}
}
