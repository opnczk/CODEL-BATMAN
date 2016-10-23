package com.sar2016.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.services.people.v1.People;
import com.sar2016.util.GooglePeopleApiUtil;

/**
 * Servlet implementation class AuthGivenServlet
 */
public class AuthGivenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthGivenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("access_token") == null){
		RequestDispatcher rd = request.getRequestDispatcher( "authGiven.html" );
		rd.forward(request, response);
		}else{
			response.getWriter().println(request.getParameter("access_token"));
			response.getWriter().append("Served at: ").append(request.getContextPath());
			GooglePeopleApiUtil gpau = new GooglePeopleApiUtil();
			String code = request.getParameter("access_token");
			response.getWriter().println(request);
			response.getWriter().println(code);
			People people = gpau.setUpAuthGiven(code);

			response.getWriter().println(people);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
