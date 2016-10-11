package com.sar2016.entities;

public class Enterprise extends Contact{
	long id;
	int numSiret;
	
	public Enterprise() {
		
	}
	
	public Enterprise (String nom, String mail, String siret)
	{
		this.numSiret = Integer.parseInt(siret);
		this.firstName = nom;
		this.email = mail;
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
