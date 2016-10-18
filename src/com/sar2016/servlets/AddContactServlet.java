package com.sar2016.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.sar2016.services.AddressService;
import com.sar2016.services.ContactService;
import com.sar2016.services.PhoneNumberService;
import com.sar2016.util.HibernateUtil;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String nickName = request.getParameter("nickname");
		String email = request.getParameter("email");
		
		boolean success = true;
		Exception catchedException = null;
		try{
			ContactService service = new ContactService();
			
			service.create(firstName, lastName, nickName, email, null);
			
			String city = request.getParameter( "city" );
			String street = request.getParameter( "street" );
			String zip = request.getParameter( "zip" );
			int country = Integer.parseInt(request.getParameter( "country" ));
			
			
			AddressService addService = new AddressService();
			
			addService.create( street,  city,  zip,  country);
			
			/*String phoneKind = request.getParameter("phoneKind"); 
			String phoneNumber = request.getParameter("phoneNumber");  
			
			PhoneNumberService phoneService = new PhoneNumberService();
			
			phoneService.create(phoneKind, phoneNumber);*/
		}catch(Exception e){
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
		}finally{
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			//session.getTransaction().rollback();
			if(session.isOpen())
				session.close();
		}
		
		
		PrintWriter writer = response.getWriter();
		
		if(success)
			writer.println("Contact ajouté avec succès.");
		else
			writer.println("Contact NON ajouté."+catchedException.getLocalizedMessage());
	}

}
