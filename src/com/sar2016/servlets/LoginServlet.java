package com.sar2016.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sar2016.dao.UserDAO;
import com.sar2016.entities.Contact;
import com.sar2016.entities.Enterprise;
import com.sar2016.entities.User;
import com.sar2016.services.ContactService;
import com.sar2016.services.EnterpriseService;
import com.sar2016.services.UserService;
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
		String token = request.getParameter( "idtoken" );
		
		
		
		UserDAO userDao = (UserDAO) ac.getBean("UserDAO");
		User user = userDao.login(name, password);
		if( user == null)
		{
			user = userDao.loginGoogle(token, name);
			if(user == null){
				User u = new User();
				UserService uservice = (UserService) ac.getBean("UserService");
				u.setFirstName(request.getParameter("first_name"));
				u.setLastName(request.getParameter("last_name"));
				u.setEmail(name);
				u.setPassword("");
				System.out.println("goode ! => création du user");
				uservice.create(u);
			}
		}
		
		if(this.redirect || user != null){
			HttpSession session = request.getSession();
			if(!this.redirect){
				session.setAttribute("logged_user", user.getId());
			}
			System.out.println("Logged_user_id "+session.getAttribute("logged_user"));
			ContactService cs = (ContactService) ac.getBean("ContactService");
			
			List<Contact> contacts = cs.getContacts((Long) request.getSession().getAttribute("logged_user"));
			EnterpriseService es = (EnterpriseService) ac.getBean("EnterpriseService");
			List<Enterprise> enterprises = es.getEnterprises((Long) request.getSession().getAttribute("logged_user"));
			
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
