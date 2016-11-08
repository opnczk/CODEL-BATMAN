package com.sar2016.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class UpdateContactServlet
 */
public class UpdateContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Transactional
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
