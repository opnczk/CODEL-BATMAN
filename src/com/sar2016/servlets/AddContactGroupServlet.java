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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sar2016.dao.ContactGroupDAO;
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

/**
 * Servlet implementation class AddContactServlet
 */	
@Transactional
public class AddContactGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContactGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Autowired
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupName = request.getParameter("group_name");
		
		boolean success = true;
		Exception catchedException = null;
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			
			//int nbPhones = Integer.parseInt(request.getParameter("nb_phones"));
			//System.out.println("NBPhones "+nbPhones);

				ContactGroupService service = null;
				ContactGroup c = null;
				service = (ContactGroupService) ac.getBean("ContactGroupService");
				service.setDao((ContactGroupDAO) ac.getBean("ContactGroupDAO"));
				c = (ContactGroup)ac.getBean("ContactGroup");
				c.setGroupName(groupName);
				c.setUser(((UserDAO)ac.getBean("UserDAO")).getById(Long.parseLong(request.getSession().getAttribute("logged_user").toString())));

				/*if(nbPhones >= 0){
					PhoneNumberService phoneService = (PhoneNumberService) ac.getBean("PhoneNumberService");

					for (int i = 0; i <= nbPhones; i++){
						String kind = request.getParameter("phones["+i+"].phoneKind");
						String number = request.getParameter("phones["+i+"].phoneNumber");
						
						if(kind != null && number != null){						
							PhoneNumber pn = (PhoneNumber) ac.getBean("PhoneNumber");
							//c.addProfile(pn);
						}
					}
				}*/
				service.create(c);		
		
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
