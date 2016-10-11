package com.sar2016.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sar2016.entities.Enterprise;
import com.sar2016.services.EnterpriseService;

/**
 * Servlet implementation class AddEnterpriseServlet
 */
public class AddEnterpriseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEnterpriseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter( "nom" );
		String mail = request.getParameter( "email" );
		String siret = request.getParameter( "siret" );
		
		
		EnterpriseService service = new EnterpriseService();
		
		service.create( nom, mail, siret);
		
		PrintWriter writer = response.getWriter();
		
		writer.println("Contact ajouté avec succès.");
	}

}
