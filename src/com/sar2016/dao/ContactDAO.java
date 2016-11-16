package com.sar2016.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import com.sar2016.entities.Contact;

@Transactional
public class ContactDAO extends HibernateDaoSupport {

	public Contact create (Contact c){
		getHibernateTemplate().persist(c);
		getHibernateTemplate().save(c);
		return c;
	}
	
	public Contact create(String firstName, String lastName, String nickName, String email) {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();

		Contact c = (Contact) ac.getBean("Contact");
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setNickName(nickName);
		c.setEmail(email);

		getHibernateTemplate().persist(c);
		getHibernateTemplate().save(c);
		return c;
	}

	public Contact getById(long id) {

		return (Contact)getHibernateTemplate().get(Contact.class, id);
	}

	public Contact getByMail(String mail) {
		//Written in HQL
		String query = "from Contact as t where t.email = :mail";

		return (Contact) getHibernateTemplate().find(query, mail);
	}

	public List<Contact> searchByMail(String mail) {
		//Written in HQL
		String query = "from Contact as t where t.email like :mail";		
		String str ="%" + mail + "%";
	
		List<Contact> rs = ((List<Contact>) getHibernateTemplate().find(query, mail));
		return rs;
	}	
	
	public void delete(Contact c){
		getHibernateTemplate().delete(c);
	}

	public List<Contact> getAll() {

		String query = "from Contact";		
		List<Contact> rs = ((List<Contact>) getHibernateTemplate().find(query));
		return rs;
	}
	
	public List<Contact> getContacts(){

		String query = "from Contact as t";
		List<Contact> rs = (List<Contact>) getHibernateTemplate().find(query);
		return (List<Contact>) rs;
	}

	public void deleteById(long id) {
		// TODO Auto-generated method stub
		
	}
}
