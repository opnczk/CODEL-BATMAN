package com.sar2016.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
 * Servlet implementation class AddContactServlet
 */	
@Transactional
public class AddContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Autowired
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		
    	RequestDispatcher rd = request.getRequestDispatcher( "AddContact.jsp" );
		
    	List<ContactGroup> contactGroups = ((ContactGroupService) ac.getBean("ContactGroupService")).getAll(Long.parseLong(request.getSession().getAttribute("logged_user").toString()));
		request.setAttribute("contactGroups", contactGroups);
		
		rd.forward(request, response);
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Autowired
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String nickName = request.getParameter("nickname");
		String email = request.getParameter("email");
		
		boolean success = true;
		Exception catchedException = null;
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		
		String placeId = request.getParameter( "PLACE_ID" );
		String lat = request.getParameter( "ADD_LAT" );
		String lng = request.getParameter( "ADD_LNG" );
		String streetNb = request.getParameter( "ADD_ST_NB" );
		String street = request.getParameter( "ADD_STREET" );
		String city = request.getParameter( "ADD_CITY" );
		String country = request.getParameter( "ADD_COUNTRY" );
		String zipcode = request.getParameter( "ADD_ZIPCODE" );
		
		Address add = null;
		if(placeId != null && placeId != ""){
			AddressService addService = (AddressService) ac.getBean("AddressService");
		
			add = (Address) ac.getBean("Address");
			add.setStreetNb(Long.parseLong(streetNb));
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

			
			if(request.getParameter("num_siret") != null && request.getParameter("companyOrNot").contentEquals("true")){
				System.out.println("AJOUT D'une entreprise");
				EnterpriseService service = null;
				Enterprise c = null;
				long numSiret = Long.parseLong(request.getParameter("num_siret"));
				
				service = (EnterpriseService) ac.getBean("EnterpriseService");
				service.setDao((EnterpriseDAO) ac.getBean("EnterpriseDAO"));
				
				c = (Enterprise) ac.getBean("Enterprise");
				c.setFirstName(firstName);
				c.setEmail(email);
				c.setNumSiret(numSiret);
				if(add != null)
					c.setAddress(add);				
				c.setUser(((UserDAO)ac.getBean("UserDAO")).getById(Long.parseLong(request.getSession().getAttribute("logged_user").toString())));

				if(nbPhones >= 0){
					PhoneNumberService phoneService = (PhoneNumberService) ac.getBean("PhoneNumberService");

					for (int i = 0; i <= nbPhones; i++){
						String kind = request.getParameter("phones["+i+"].phoneKind");
						String number = request.getParameter("phones["+i+"].phoneNumber");
						
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
				service.create(c);
			}else{
				ContactService service = null;
				Contact c = null;
				service = (ContactService) ac.getBean("ContactService");
				service.setDao((ContactDAO) ac.getBean("ContactDAO"));
				
				c = (Contact)ac.getBean("Contact");
				c.setFirstName(firstName);
				c.setLastName(lastName);
				c.setNickName(nickName);
				c.setEmail(email);
				c.setAddress(add);
				c.setUser(((UserDAO)ac.getBean("UserDAO")).getById(Long.parseLong(request.getSession().getAttribute("logged_user").toString())));

				if(nbPhones >= 0){
					PhoneNumberService phoneService = (PhoneNumberService) ac.getBean("PhoneNumberService");

					for (int i = 0; i <= nbPhones; i++){
						String kind = request.getParameter("phones["+i+"].phoneKind");
						String number = request.getParameter("phones["+i+"].phoneNumber");
						
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
				service.create(c);
			}			
		
		
		PrintWriter writer = response.getWriter();
		
		if(success){
			ContactService cs = (ContactService) ac.getBean("ContactService");

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
