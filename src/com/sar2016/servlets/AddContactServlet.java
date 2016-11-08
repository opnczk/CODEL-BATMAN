package com.sar2016.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sar2016.entities.Address;
import com.sar2016.entities.Contact;
import com.sar2016.entities.Enterprise;
import com.sar2016.entities.PhoneNumber;
import com.sar2016.services.AddressService;
import com.sar2016.services.ContactService;
import com.sar2016.services.EnterpriseService;
import com.sar2016.services.PhoneNumberService;
import com.sar2016.util.Helper;

/**
 * Servlet implementation class AddContactServlet
 */
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Transactional
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String nickName = request.getParameter("nickname");
		String email = request.getParameter("email");
		//int numSiret;
		
		boolean success = true;
		Exception catchedException = null;
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		//try{
			Contact c = null;
			ContactService service = null;
			if(request.getParameter("num_siret") != null){
				int numSiret = Integer.parseInt(request.getParameter("num_siret"));
				
				service = (EnterpriseService) ac.getBean("EnterpriseService");
				c = ((EnterpriseService)service).create(firstName, email, numSiret);
				//EnterpriseService entService = new EnterpriseService();
			}else{				
				service = (ContactService) ac.getBean("ContactService");
				//ContactService service = new ContactService();
			
				c = service.create(firstName, lastName, nickName, email);
			}
			String placeId = request.getParameter( "PLACE_ID" );
			String lat = request.getParameter( "ADD_LAT" );
			String lng = request.getParameter( "ADD_LNG" );
			String streetNb = request.getParameter( "ADD_ST_NB" );
			String street = request.getParameter( "ADD_STREET" );
			String city = request.getParameter( "ADD_CITY" );
			String country = request.getParameter( "ADD_COUNTRY" );
			String zipcode = request.getParameter( "ADD_ZIPCODE" );
			System.out.println("------------------------------------------------------------Ici");
			if(placeId != null && placeId != ""){
				AddressService addService = (AddressService) ac.getBean("AddressService");

				//AddressService addService = new AddressService();
			
				Address add = addService.create(placeId, lat, lng, streetNb+" "+street,  city,  zipcode,  country);
				c.setAddress(add);
				service.getDao().getHibernateTemplate().saveOrUpdate(c);
				//Helper.hibernateUpdateObject(c);
			}
			

			int nbPhones = Integer.parseInt(request.getParameter("nb_phones"));
			System.out.println("NBPhones "+nbPhones);
			if(nbPhones >= 0){
				PhoneNumberService phoneService = (PhoneNumberService) ac.getBean("PhoneNumberService");

				//PhoneNumberService phoneService = new PhoneNumberService();
				for (int i = 0; i <= nbPhones; i++){
					String kind = request.getParameter("phones["+i+"].phoneKind");
					String number = request.getParameter("phones["+i+"].phoneNumber");
					
					if(kind != null && number != null){						
						PhoneNumber numberObj = phoneService.create(kind, number);
						c.addProfile(numberObj);
						service.getDao().getHibernateTemplate().saveOrUpdate(c);
						phoneService.getDao().getHibernateTemplate().saveOrUpdate(numberObj);
					}
				}
			}
			
		/*}catch(Exception e){
			System.out.println("Erreur catchée");
			success = false;
			catchedException = e;
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			if(session.getTransaction().isActive()){
				session.getTransaction().rollback();
			}else{
				session.getTransaction().begin();
				session.getTransaction().rollback();
			}
			if(session.isOpen())
				session.close();
			throw e;
		}finally{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			//session.getTransaction().rollback();
			if(session.isOpen())
				session.close();
		}*/
		
		
		PrintWriter writer = response.getWriter();
		
		if(success){
			ContactService cs = (ContactService) ac.getBean("ContactService");

			//ContactService cs = new ContactService();
			List<Contact> contacts = cs.getContacts();
			EnterpriseService es = (EnterpriseService) ac.getBean("EnterpriseService");

			//EnterpriseService es = new EnterpriseService();
			List<Enterprise> enterprises = es.getEnterprises();
			
			RequestDispatcher rd = request.getRequestDispatcher( "Main.jsp" );
			
			request.setAttribute("contacts", contacts);
			request.setAttribute("enterprises", enterprises);
			
			rd.forward(request, response);
		}else{
			writer.println("Contact NON ajouté."+catchedException.getLocalizedMessage());
		}
	}

}
