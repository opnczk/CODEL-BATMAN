package com.sar2016.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.Address;
import com.sar2016.entities.Contact;

@Transactional
public class AddressDAO extends HibernateDaoSupport {
	
	public AddressDAO(){
		
	}
	
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

	public List<Address> searchByPart(String str) {
		//Written in HQL
		String query = "from Address as t where t.street = ? or t.city = ? or  t.zip = ? or  t.placeId = ? or  t.country = ?";		
		String param ="%"+str+"%";
	
		List<Address> rs = ((List<Address>) getHibernateTemplate().find(query, "%"+param+"%", "%"+param+"%", "%"+param+"%", "%"+param+"%", "%"+param+"%"));
		return rs;
	}
}