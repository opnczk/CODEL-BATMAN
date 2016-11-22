package com.sar2016.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sar2016.dao.ContactDAO;
import com.sar2016.dao.EnterpriseDAO;
import com.sar2016.dao.UserDAO;
import com.sar2016.entities.Address;
import com.sar2016.entities.Contact;
import com.sar2016.entities.ContactGroup;
import com.sar2016.entities.Enterprise;
import com.sar2016.entities.PhoneNumber;
import com.sar2016.services.AddressService;
import com.sar2016.services.ContactGroupService;
import com.sar2016.services.ContactService;
import com.sar2016.services.EnterpriseService;
import com.sar2016.services.PhoneNumberService;
import com.sar2016.services.UserService;

/**
 * Servlet implementation class UpdateContactServlet
 */
public class UpdateContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateContactServlet() {
    	
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Transactional
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		long id = Long.parseLong(request.getParameter("id"));
		ContactService cs = (ContactService)ac.getBean("ContactService");
		Contact c = cs.getById(id);
		
		if(c != null){
			request.setAttribute("modify", true);
			request.setAttribute("contact", c);
			
			Address add = c.getAddress();
			System.out.println("Address" + add);
			if(add != null)
				request.setAttribute("contact-address", add);
				
			RequestDispatcher rd = request.getRequestDispatcher( "AddContact.jsp" );
			
			List<ContactGroup> contactGroups = ((ContactGroupService) ac.getBean("ContactGroupService")).getAll(Long.parseLong(request.getSession().getAttribute("logged_user").toString()));
			request.setAttribute("contactGroups", contactGroups);
			
			rd.forward(request, response);
		}
	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Transactional
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String str = request.getParameter("contact_id");
    	str.replace("\"", "");
    	str.trim();
		long id = Long.parseLong(str);
		
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ContactService cs = (ContactService)ac.getBean("ContactService");

		cs.setDao((ContactDAO) ac.getBean("ContactDAO"));
		
		Enterprise e = null;
		Contact c = null;
		Address add = null;
		
		if(cs.getById(id) instanceof Contact){
			c = cs.getById(id);
			add = c.getAddress();
		}else if(cs.getById(id) instanceof Enterprise){
			e = (Enterprise)cs.getById(id);
			add = e.getAddress();
		}
		
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String nickName = request.getParameter("nickname");
		String email = request.getParameter("email");
		
		boolean success = true;
		Exception catchedException = null;
		
		String placeId = request.getParameter( "PLACE_ID" );
		String lat = request.getParameter( "ADD_LAT" );
		String lng = request.getParameter( "ADD_LNG" );
		String streetNb = request.getParameter( "ADD_ST_NB" );
		String street = request.getParameter( "ADD_STREET" );
		String city = request.getParameter( "ADD_CITY" );
		String country = request.getParameter( "ADD_COUNTRY" );
		String zipcode = request.getParameter( "ADD_ZIPCODE" );		
		
		System.out.println("Coucou modif !");		
		
		if(placeId != null && placeId != "" && (add == null || (!placeId.contentEquals(add.getPlaceId()) ) )){
			AddressService addService = (AddressService) ac.getBean("AddressService");
		
			if(add == null)
				add = (Address) ac.getBean("Address");
			
			if(streetNb != ""){
				add.setStreetNb(Long.parseLong(streetNb));
			}else{
				add.setStreetNb(0);
			}
			add.setCity(city);
			add.setCountry(country);
			add.setPlaceId(placeId);
			add.setStreet(street);
			add.setZip(zipcode);
			add.setLat(lat);
			add.setLng(lng);
			
		}
			int nbPhones = Integer.parseInt(request.getParameter("nb_phones"));
			System.out.println("NBPhones "+nbPhones);

			
			if( e != null){
				System.out.println("Modification d'une entreprise");
				EnterpriseService service = null;
				
				long numSiret = Long.parseLong(request.getParameter("num_siret"));
				
				service = (EnterpriseService) ac.getBean("EnterpriseService");
				service.setDao((EnterpriseDAO) ac.getBean("EnterpriseDAO"));
				
				//c = (Enterprise) ac.getBean("Enterprise");
				if(!e.getFirstName().contentEquals(firstName))
					e.setFirstName(firstName);

				if(!e.getEmail().contentEquals(email))
					e.setEmail(email);

				if(e.getNumSiret() != numSiret)
					e.setNumSiret(numSiret);
				
				if(add != null)
					e.setAddress(add);				
				c.setUser(((UserDAO)ac.getBean("UserDAO")).getById(Long.parseLong(request.getSession().getAttribute("logged_user").toString())));

				if(nbPhones >= 0){
					//e.getProfiles().removeAll(e.getProfiles());
					//service.update(e);
					
					//PhoneNumberService phoneService = (PhoneNumberService) ac.getBean("PhoneNumberService");
					
					for (int i = 0; i <= nbPhones; i++){
						//boolean updated = false;
						Iterator<PhoneNumber> it = ((((Enterprise)cs.getById(id)).getProfiles())).iterator();
						while (it.hasNext()){
							String kind = request.getParameter("phones["+i+"].phoneKind");
							String number = request.getParameter("phones["+i+"].phoneNumber");
							long phoneId = Long.parseLong(request.getParameter("phones["+i+"].phoneId"));
							
							if (phoneId == (it.next()).getId()){
							//String k = it.next().getPhoneKind();
							//String n = it.next().getPhoneNumber();
								(it.next()).setPhoneNumber(number);
								(it.next()).setPhoneKind(kind);
							//PhoneNumber pn = it.next();
							//if(kind != null && number != null && updated == false){
								//if(!kind.isEqual(k) ^ !number.isEqual(n)){
									//PhoneNumber pn = (PhoneNumber) ac.getBean("PhoneNumber");
									//TODO remove profile first
									//set contact num and kind 
									//pn.setContact(e);
									//pn.setPhoneKind(kind);
									//pn.setPhoneNumber(number);
									//add it to contact profiles
									//e.addProfile(pn);
									//updated = true;
								/*}else{
									if(){}
								}*/
							}else{
							
								
							}
						}
					}
				}
				ContactGroup cg = null;
				ContactGroupService cgService = (ContactGroupService) ac.getBean("ContactGroupService");
				if(request.getParameter("groupName") != ""){
					cg = (ContactGroup) ac.getBean("ContactGroup");
					cg.setGroupName(request.getParameter("groupName"));
					cg.setUser(((UserService) ac.getBean("UserService")).getById((Long) request.getSession().getAttribute("logged_user")));
					cgService.create(cg);
				}else if(request.getParameter("contactGroup") != ""){
					String tempStr = request.getParameter("contactGroup");
					tempStr.trim();
					cg = cgService.getById(Long.parseLong(tempStr));
					System.out.println("ContactGroup"+cg);	
				}
				cg.addContact(e);
				c.addBook(cg);
				service.update(e);
				/* end of enterprise */
			}else if(c != null){
				System.out.println("Modification d'un Contact");
				
				//c = (Contact)ac.getBean("Contact");
				if(c.getFirstName() != null && firstName != null && !c.getFirstName().contentEquals(firstName))
					c.setFirstName(firstName);
				
				if(c.getLastName() != null && lastName != null && !c.getLastName().contentEquals(lastName))
				c.setLastName(lastName);
				
				if(c.getNickName() != null && nickName != null && !c.getNickName().contentEquals(nickName))
				c.setNickName(nickName);
				
				if(c.getEmail() != null && !c.getEmail().contentEquals(email))
				c.setEmail(email);
				
				if(add != null)
					c.setAddress(add);
				c.setUser(((UserDAO)ac.getBean("UserDAO")).getById(Long.parseLong(request.getSession().getAttribute("logged_user").toString())));

				if(nbPhones >= 0){
					c.setProfiles(new HashSet<PhoneNumber>());
					//cs.update(c);
					
					PhoneNumberService phoneService = (PhoneNumberService) ac.getBean("PhoneNumberService");

					for (int i = 0; i <= nbPhones; i++){
						String kind = request.getParameter("phones["+i+"].phoneKind");
						String number = request.getParameter("phones["+i+"].phoneNumber");
						long phoneId = Long.parseLong(request.getParameter("phones["+i+"].phoneId"));
						
						if(kind != null && number != null){						
							PhoneNumber pn = (PhoneNumber) ac.getBean("PhoneNumber");
							pn.setContact(c);
							pn.setPhoneKind(kind);
							pn.setPhoneNumber(number);
							c.addProfile(pn);
						}
					}
				}
				ContactGroup cg = null;
				ContactGroupService cgService = (ContactGroupService) ac.getBean("ContactGroupService");
				if(request.getParameter("groupName") != ""){
					cg = (ContactGroup) ac.getBean("ContactGroup");
					cg.setGroupName(request.getParameter("groupName"));
					cg.setUser(((UserService) ac.getBean("UserService")).getById((Long) request.getSession().getAttribute("logged_user")));
					cgService.create(cg);
				}else if(request.getParameter("contactGroup") != ""){
					String tempStr = request.getParameter("contactGroup");
					tempStr.trim();
					cg = cgService.getById(Long.parseLong(tempStr));
					System.out.println("ContactGroup"+cg);	
				}
				cg.addContact(c);
				c.addBook(cg);
				cs.update(c);
			}			
		
		
		PrintWriter writer = response.getWriter();
		
		if(success){
			
			List<Contact> contacts = cs.getContacts((Long) request.getSession().getAttribute("logged_user"));
			EnterpriseService es = (EnterpriseService) ac.getBean("EnterpriseService");

			List<Enterprise> enterprises = es.getEnterprises((Long) request.getSession().getAttribute("logged_user"));
			
			RequestDispatcher rd = request.getRequestDispatcher( "Main.jsp" );
			
			request.setAttribute("contacts", contacts);
			request.setAttribute("enterprises", enterprises);
			
			rd.forward(request, response);
		}else{
			Exception catchedException2 = catchedException;
			writer.println("Contact NON ajouté."+catchedException2.getLocalizedMessage());
		}

	}

}
