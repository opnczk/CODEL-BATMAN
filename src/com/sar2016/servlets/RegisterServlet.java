package com.sar2016.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sar2016.entities.Contact;
import com.sar2016.entities.ContactGroup;
import com.sar2016.entities.Enterprise;
import com.sar2016.entities.User;
import com.sar2016.services.ContactGroupService;
import com.sar2016.services.ContactService;
import com.sar2016.services.EnterpriseService;
import com.sar2016.services.UserService;
/**
 * Servlet implementation class LoginServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private boolean redirect = false;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		boolean success = false;
		Exception catchedException = null;
		String firstName = request.getParameter( "first_name" );
		String lastName = request.getParameter( "last_name" );
		String email = request.getParameter( "email" );
		String password = request.getParameter( "password" );
		String confirm = request.getParameter( "confirm" );
		
		if (firstName != "" && lastName != "" && email != ""){
		
			if( password != null && password.compareTo(confirm) == 0){
				User u = (User) ac.getBean("User");
				UserService uservice = (UserService) ac.getBean("UserService");
				u.setFirstName(firstName);
				u.setLastName(lastName);
				u.setEmail(email);
				u.setPassword(password);
				uservice.create(u);
				success =true;
				
				ContactGroupService cgservice = (ContactGroupService) ac.getBean("ContactGroupService");
				ContactGroup cgWork = (ContactGroup) ac.getBean("ContactGroup");
				cgWork.setGroupName("Work");
				cgWork.setUser(u);
				ContactGroup cgFamily = (ContactGroup) ac.getBean("ContactGroup");
				cgFamily.setGroupName("Family");
				cgFamily.setUser(u);
				ContactGroup cgFriends = (ContactGroup) ac.getBean("ContactGroup");
				cgFriends.setGroupName("Friends");
				cgFriends.setUser(u);
				
				cgservice.create(cgWork);
				cgservice.create(cgFamily);
				cgservice.create(cgFriends);
				
				request.getSession().setAttribute("logged_user", u.getId());
			}
		}else{
			System.out.println("BAD ! => rejet");
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
