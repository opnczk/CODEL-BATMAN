package com.sar2016.dao;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.web.context.ContextLoader;

import com.sar2016.entities.Address;

public class AddressDAO extends HibernateDaoSupport {
	
	public AddressDAO(){
		
	}
	public Address create(String placeId,String lat,String lng, String street, String city, String zip, String country) {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		Address c = (Address) ac.getBean("Address");
		c.setPlaceId(placeId);
		c.setStreet(street);
		c.setCity(city);
		c.setZip(zip);
		c.setCountry(country);
		
		((Session) getHibernateTemplate().getSessionFactory()).save(c);
		//Address c = new Address(placeId, lat, lng, street, city, zip, country);
		
		//Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		/*Transaction tx = null;
		if(!session.getTransaction().isActive()){
			tx = session.beginTransaction();
		}else{
			tx = session.getTransaction();
		}
		
		
		session.save(c);
		
		tx.commit();
		*/
		return c;
	}

	public Address getById(long id) {
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		
		/*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx= session.beginTransaction();
		
		return (Address)session.get(Address.class, id);
		*/
		return (Address)((Session) getHibernateTemplate().getSessionFactory()).get(Address.class, id);
	}
	
	public void deleteById(long id){
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();

		/*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		
		session.delete(id);
		*/
		((Session) getHibernateTemplate().getSessionFactory()).delete(id);
	}
}