package com.sar2016.entities;

import java.util.Set;

public class Contact {
	long id;
	String firstName, lastName, nickName, email;
	Address address;
	private Set<ContactGroup> books;
	private Set<PhoneNumber> profiles;
	
	public Contact()
	{
		this.firstName = null;
		this.lastName = null;
		this.nickName = null;
		this.email = null;
		System.out.println("Null POJO Construct Call.");
		System.out.println(this);
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Contact ( String firstName, String lastName, String nickName, String email, Address address)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.address = address;
		this.email = email;
		System.out.println(this);
	}
	
	public String getMail()
	{
		return this.email;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Set<ContactGroup> getBooks(){
		return this.books;
	}
	
	public void setBooks(Set<ContactGroup> groups){
		this.books = groups;
	}
	
	public void addBook(ContactGroup group){
		this.books.add(group);
		if(!group.getContacts().contains(this))
			group.addContact(this);
	}
	
	public void removeBook(ContactGroup group){
		this.books.remove(group);
		if(group.getContacts().contains(this))
			group.removeContact(this);
	}
	
	public Set<PhoneNumber> getProfiles(){
		return this.profiles;
	}
	
	public void setProfiles(Set<PhoneNumber> numbers){
		this.profiles = numbers;
	}
	
	public void addProfile(PhoneNumber phoneNumber) {
		this.profiles.add(phoneNumber);
	}
	
	@Override
	public String toString()
	{
		return "MemRef :"+super.toString()+" - FName :"+this.firstName+" - LName :"+this.lastName;
	}

	
}
