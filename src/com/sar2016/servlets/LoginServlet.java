package com.sar2016.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sar2016.entities.Contact;
import com.sar2016.entities.Enterprise;
import com.sar2016.services.ContactService;
import com.sar2016.services.EnterpriseService;
/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private boolean redirect = false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.redirect = true;
		this.doPost(request, response);
	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		String name = request.getParameter( "name" );
		String password = request.getParameter( "password" );
		
		if(this.redirect || name.equals(password)){			
			ContactService cs = (ContactService) ac.getBean("ContactService");
			
			List<Contact> contacts = cs.getContacts();
			EnterpriseService es = (EnterpriseService) ac.getBean("EnterpriseService");
			List<Enterprise> enterprises = es.getEnterprises();
			
			RequestDispatcher rd = request.getRequestDispatcher( "Main.jsp" );
			
			request.setAttribute("contacts", contacts);
			request.setAttribute("enterprises", enterprises);
			
			rd.forward(request, response);
		}else {
			System.out.println("Fail");
			// if not, redirect to index
			response.sendRedirect( "index.html" );
		}
	}
}
