package com.sar2016.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sar2016.util.GooglePeopleApiUtil;
import com.sar2016.util.PeopleQuickstart;

/**
 * Servlet implementation class AuthRequestServlet
 */
public class AuthRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GooglePeopleApiUtil gpau = new GooglePeopleApiUtil();
		String authRequestUrl = gpau.setUpAuthRequest();
		response.sendRedirect(authRequestUrl);
		//System.out.println("TEST API");
		//(new PeopleQuickstart()).testAPI();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
