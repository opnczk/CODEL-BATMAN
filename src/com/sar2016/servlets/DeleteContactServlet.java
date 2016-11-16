package com.sar2016.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sar2016.entities.Contact;
import com.sar2016.services.ContactService;

/**
 * Servlet implementation class DeleteContactServlet
 */
@Transactional
public class DeleteContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		long id = Long.parseLong(request.getParameter("id"));
		ContactService cs = (ContactService) ac.getBean("ContactService");
		Contact c= cs.getById(id);
		System.out.println(c.toString());
		cs.delete(c);
		response.sendRedirect("home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
