package com.sar2016.servlets;

import java.io.IOException;
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
import com.sar2016.entities.ContactGroup;
import com.sar2016.services.ContactGroupService;
import com.sar2016.services.ContactService;

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
		Contact c = cs.getById(id);
		System.out.println("Coucou modif !");
	}

}
