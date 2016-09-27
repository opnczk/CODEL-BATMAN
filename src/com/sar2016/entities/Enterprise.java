package com.sar2016.entities;

public class Enterprise {
	long id;
	int numSiret;
	
	public Enterprise() {
		
	}
	
	public Enterprise(int numSiret) {
		this.numSiret = numSiret;
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
