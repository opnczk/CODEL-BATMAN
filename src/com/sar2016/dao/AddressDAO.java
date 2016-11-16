package com.sar2016.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.Address;

public class AddressDAO extends HibernateDaoSupport {
	
	public AddressDAO(){
		
	}
	@Transactional (readOnly = false)
	public Address create(String placeId,String lat,String lng, String street, String city, String zip, String country) {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		getHibernateTemplate().setCheckWriteOperations(false);

		Address c = (Address) ac.getBean("Address");
		c.setPlaceId(placeId);
		c.setStreet(street);
		c.setCity(city);
		c.setZip(zip);
		c.setCountry(country);
		
		getHibernateTemplate().save(c);
		return c;
	}

	public Address getById(long id) {
		
		return (Address)getHibernateTemplate().get(Address.class, id);
	}
	
	public void deleteById(long id){

		getHibernateTemplate().delete(id);
	}
}