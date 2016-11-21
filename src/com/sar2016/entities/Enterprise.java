package com.sar2016.entities;

public class Enterprise extends Contact{
	long id;
	long numSiret;
	
	public Enterprise() {
		
	}
	
	public Enterprise (String nom, String mail, String siret)
	{
		this.numSiret = Integer.parseInt(siret);
		this.firstName = nom;
		this.email = mail;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(long numSiret) {
		this.numSiret = numSiret;
	}

}
