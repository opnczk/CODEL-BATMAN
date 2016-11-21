package com.sar2016.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sar2016.dao.UserDAO;
import com.sar2016.entities.Contact;
import com.sar2016.entities.Enterprise;
import com.sar2016.services.ContactService;
import com.sar2016.services.EnterpriseService;

/**
 * Servlet implementation class ImportContactServlet
 */
public class ImportContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		
		int nbContacts = Integer.parseInt(request.getParameter("nb_contacts"));
		System.out.println("Nb_Contacts : "+nbContacts);
		
		ContactService service = (ContactService) ac.getBean("ContactService");
		for(int i = 0; i < nbContacts; i++){
				System.out.println(request.getParameter("contacts["+i+"].email")+" "+request.getParameter("contacts["+i+"].last_name")+" "+request.getParameter("contacts["+i+"].first_name"));
				try{
					Contact c = (Contact) ac.getBean("Contact");
					c.setFirstName(request.getParameter("contacts["+i+"].first_name"));
					c.setLastName(request.getParameter("contacts["+i+"].last_name"));
					c.setEmail(request.getParameter("contacts["+i+"].email"));
					c.setUser(((UserDAO)ac.getBean("UserDAO")).getById((Long) request.getSession().getAttribute("logged_user")));

					service.create(c);
					
				}catch(Exception e){
					System.out.println("Exception"+e.getMessage());
					e.printStackTrace();
				}
		}
		
		
		List<Contact> contacts = service.getContacts((Long) request.getSession().getAttribute("logged_user"));
		
		EnterpriseService es = (EnterpriseService) ac.getBean("EnterpriseService");
		List<Enterprise> enterprises = es.getEnterprises((Long) request.getSession().getAttribute("logged_user"));
		
		RequestDispatcher rd = request.getRequestDispatcher( "Main.jsp" );
		
		request.setAttribute("contacts", contacts);
		request.setAttribute("enterprises", enterprises);
		
		rd.forward(request, response);
	}

}
