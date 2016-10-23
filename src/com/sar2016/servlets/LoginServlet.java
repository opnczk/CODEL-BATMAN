package com.sar2016.servlets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleBrowserClientRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.people.v1.People;
import com.sar2016.entities.Contact;
import com.sar2016.entities.Enterprise;
import com.sar2016.services.ContactService;
import com.sar2016.services.EnterpriseService;
import com.sar2016.services.UserService;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
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
		String name = request.getParameter( "name" );
		String password = request.getParameter( "password" );
		String idToken = request.getParameter( "idtoken" );
		
		UserService us = new UserService();
		
		/*if(name.equals(password)){
			us.create("Olivier", "Panczuk", "opanczuk@gmail.com", "root");
		}*/
		
		if(this.redirect || idToken != "" || name.equals(password)){
			System.out.println("Google Id Token");
			System.out.println(idToken);
			this.testAPI(idToken);
			ContactService cs = new ContactService();
			List<Contact> contacts = cs.getContacts();
			
			EnterpriseService es = new EnterpriseService();
			List<Enterprise> enterprises = es.getEnterprises();
			
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

	private void testAPI(String idTokenString){
		
		// TODO use a similar method to save a new user in database if the user doesn't exist.
	    String CLIENT_ID = "128124732452-h8ja44bqta91s95ui0empsgde5nj122i.apps.googleusercontent.com";
	    HttpTransport httpTransport = new NetHttpTransport();
	    JacksonFactory jsonFactory = new JacksonFactory();
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
			    .setAudience(Arrays.asList(CLIENT_ID))
			    .build();

			GoogleIdToken idToken = null;
			
			try {
				idToken = verifier.verify(idTokenString);
			} catch (GeneralSecurityException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (idToken != null) {
			  Payload payload = idToken.getPayload();

			  // Print user identifier
			  String userId = payload.getSubject();
			  System.out.println("User ID: " + userId);

			  // Get profile information from payload
			  String email = payload.getEmail();
			  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			  String name = (String) payload.get("name");
			  String pictureUrl = (String) payload.get("picture");
			  String locale = (String) payload.get("locale");
			  String familyName = (String) payload.get("family_name");
			  String givenName = (String) payload.get("given_name");

			  System.out.println(email+" "+emailVerified+" "+name+" "+familyName+" "+givenName);
			  // Use or store profile information
			  // ...

			} else {
			  System.out.println("Invalid ID token.");
			}
	}
	
	 public void setUp() throws IOException {
		    HttpTransport httpTransport = new NetHttpTransport();
		    JacksonFactory jsonFactory = new JacksonFactory();

		    // Go to the Google API Console, open your application's
		    // credentials page, and copy the client ID and client secret.
		    // Then paste them into the following code.
		    String clientId = "128124732452-h8ja44bqta91s95ui0empsgde5nj122i.apps.googleusercontent.com";
		    String clientSecret = "6qlRPKgljuED0dI2XglPcMAs";

		    // Or your redirect URL for web based applications.
		    String redirectUrl = "urn:ietf:wg:oauth:2.0:oob";
		    String scope = "https://www.googleapis.com/auth/contacts.readonly";

		    // Step 1: Authorize -->
		    String authorizationUrl = new GoogleBrowserClientRequestUrl(clientId,
		                                                                redirectUrl,
		                                                                Arrays.asList(scope))
		        .build();

		    // Point or redirect your user to the authorizationUrl.
		    System.out.println("Go to the following link in your browser:");
		    System.out.println(authorizationUrl);

		    // Read the authorization code from the standard input stream.
		    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		    System.out.println("What is the authorization code?");
		    String code = in.readLine();
		    // End of Step 1 <--

		    // Step 2: Exchange -->
		    GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
		        httpTransport, jsonFactory, clientId, clientSecret, code, redirectUrl).execute();
		    // End of Step 2 <--

		    GoogleCredential credential = new GoogleCredential.Builder()
		        .setTransport(httpTransport)
		        .setJsonFactory(jsonFactory)
		        .setClientSecrets(clientId, clientSecret)
		        .build()
		        .setFromTokenResponse(tokenResponse);

		    People peopleService = new People.Builder(httpTransport, jsonFactory, credential)
		        .build();
		  }
	
}
