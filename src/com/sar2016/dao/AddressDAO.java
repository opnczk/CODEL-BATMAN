package com.sar2016.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.Address;

@Transactional
public class AddressDAO extends HibernateDaoSupport {
	
	public AddressDAO(){
		
	}
	
	public Address create(Address a){
		getHibernateTemplate().persist(a);
		getHibernateTemplate().save(a);
		return a;
	}
	
	public Address create(String placeId,String lat,String lng, String street, String city, String zip, String country, long streetNb) {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();

		Address a = (Address) ac.getBean("Address");
		a.setPlaceId(placeId);
		a.setStreet(street);
		a.setCity(city);
		a.setZip(zip);
		a.setCountry(country);
		a.setStreetNb(streetNb);
		
		getHibernateTemplate().persist(a);
		getHibernateTemplate().save(a);
		return a;
	}

	public Address getById(long id) {
		return (Address)getHibernateTemplate().get(Address.class, id);
	}
	
	public void deleteById(long id){
		getHibernateTemplate().delete(id);
	}

	public List<Address> searchByPart(String str) {
		//Written in HQL
		String query = "from Address as t where t.street = ? or t.city = ? or  t.zip = ? or  t.placeId = ? or  t.country = ?";		
		String param ="%"+str+"%";
		List<Address> rs = ((List<Address>) getHibernateTemplate().find(query, "%"+param+"%", "%"+param+"%", "%"+param+"%", "%"+param+"%", "%"+param+"%"));
		return rs;
	}
}