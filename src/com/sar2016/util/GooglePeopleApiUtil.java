package com.sar2016.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleBrowserClientRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.People;

public class GooglePeopleApiUtil {
	private static String CLIENT_ID = "128124732452-h8ja44bqta91s95ui0empsgde5nj122i.apps.googleusercontent.com";
	private static String CLIENT_SECRET = "6qlRPKgljuED0dI2XglPcMAs";
	static String CLIENT_SECRET_JSON = "{\"web\":{\"client_id\":\"128124732452-h8ja44bqta91s95ui0empsgde5nj122i.apps.googleusercontent.com\",\"project_id\":\"agenda-codel\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://accounts.google.com/o/oauth2/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_secret\":\"6qlRPKgljuED0dI2XglPcMAs\",\"redirect_uris\":[\"http://localhost:8080\",\"http://quailshillstudio.com/authGiven\"],\"javascript_origins\":[\"http://localhost:8080\"]}}";
	private NetHttpTransport httpTransport;
	private JacksonFactory jsonFactory;
	
	public GooglePeopleApiUtil(){
		httpTransport = new NetHttpTransport();
	    jsonFactory = new JacksonFactory();
	}
	
	public void testAPI(String idTokenString){
		
		// TODO use a similar method to save a new user in database if the user doesn't exist.
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
	
	 /*public void setUp() throws IOException {

		    // Or your redirect URL for web based applications.
		    String redirectUrl = "http://quailshillstudio.com/authGiven";
		    String scope = "https://www.googleapis.com/auth/contacts.readonly";

		    // Step 1: Authorize -->
		    String authorizationUrl = new GoogleBrowserClientRequestUrl(CLIENT_ID,
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
		        httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, code, redirectUrl).execute();
		    // End of Step 2 <--

		    GoogleCredential credential = new GoogleCredential.Builder()
		        .setTransport(httpTransport)
		        .setJsonFactory(jsonFactory)
		        .setClientSecrets(CLIENT_ID, CLIENT_SECRET)
		        .build()
		        .setFromTokenResponse(tokenResponse);

		    People peopleService = new People.Builder(httpTransport, jsonFactory, credential)
		        .build();
		  }*/

	public String setUpAuthRequest() {
		    // Or your redirect URL for web based applications.
		    String redirectUrl = "http://quailshillstudio.com/authGiven";
		    String scope = "https://www.googleapis.com/auth/contacts.readonly";

		    // Step 1: Authorize -->
		    String authorizationUrl = new GoogleBrowserClientRequestUrl(CLIENT_ID,
		                                                                redirectUrl,
		                                                                Arrays.asList(scope))
		        .build();

		    // Point or redirect your user to the authorizationUrl.
		    //System.out.println("Go to the following link in your browser:");
		    //System.out.println(authorizationUrl);
		    return authorizationUrl;
	}

	public People setUpAuthGiven(String code) {
		 // Read the authorization code from the standard input stream.
	    /*BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("What is the authorization code?");
	    String code = in.readLine();*/
	    // End of Step 1 <--

	    // Step 2: Exchange -->
	    GoogleTokenResponse tokenResponse = null;
		try {
			tokenResponse = new GoogleAuthorizationCodeTokenRequest(
			    httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, code, this.setUpAuthRequest()).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    // End of Step 2 <--

		if(tokenResponse != null){
	    GoogleCredential credential = new GoogleCredential.Builder()
	        .setTransport(httpTransport)
	        .setJsonFactory(jsonFactory)
	        .setClientSecrets(CLIENT_ID, CLIENT_SECRET)
	        .build()
	        .setFromTokenResponse(tokenResponse);

	    People peopleService = new People.Builder(httpTransport, jsonFactory, credential)
	        .build();
	    return peopleService;
	    }
		System.out.println("NULL ");
		return null;
	}

	public static String getCLIENT_SECRET_JSON() {
		return CLIENT_SECRET_JSON;
	}

	public static void setCLIENT_SECRET_JSON(String cLIENT_SECRET_JSON) {
		CLIENT_SECRET_JSON = cLIENT_SECRET_JSON;
	}
	
}
