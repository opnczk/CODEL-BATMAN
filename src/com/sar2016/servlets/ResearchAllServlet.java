package com.sar2016.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sar2016.entities.Address;
import com.sar2016.entities.Contact;
import com.sar2016.entities.ContactGroup;
import com.sar2016.entities.PhoneNumber;
import com.sar2016.services.AddressService;
import com.sar2016.services.ContactGroupService;
import com.sar2016.services.ContactService;
import com.sar2016.services.PhoneNumberService;

/**
 * Servlet implementation class ResearchContactServlet
 */
public class ResearchAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResearchAllServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		PrintWriter out = response.getWriter();
		ContactService cs = (ContactService)ac.getBean("ContactService");
		ContactGroupService cgs = (ContactGroupService)ac.getBean("ContactGroupService");
		PhoneNumberService pns = (PhoneNumberService)ac.getBean("PhoneNumberService");
		AddressService as = (AddressService)ac.getBean("AddressService");
		
		List<Contact> resultsC = cs.getByPart((String)request.getParameter("param"));
		List<ContactGroup> resultsCG = cgs.getByPart((String)request.getParameter("param"));
		List<PhoneNumber> resultsPN = pns.getByPart((String)request.getParameter("param"));
		List<Address> resultsA = as.getByPart((String)request.getParameter("param"));
		
		List<Object> results = new ArrayList<Object>();
				results.addAll(resultsC);
				results.addAll(resultsCG);
				results.addAll(resultsPN);
		for(Object r:results){
			out.println("<div>"+r.toString()+"</div>");
		}
		//RequestDispatcher rd = request.getRequestDispatcher( "AllSearchResults.jsp" );
		
		//request.setAttribute("results", results);		
		//rd.forward(request, response);
	}

}
