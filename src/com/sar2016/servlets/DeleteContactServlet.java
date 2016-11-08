package com.sar2016.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sar2016.services.ContactService;

/**
 * Servlet implementation class DeleteContactServlet
 */
public class DeleteContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

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
    @Transactional
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		ContactService cs = (ContactService) ac.getBean("ContactService");
		//ContactService cs = new ContactService();
		cs.deleteById(id);
		response.sendRedirect("home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
