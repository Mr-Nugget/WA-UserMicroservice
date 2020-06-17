package com.wildadventure.user.models;

/**
 * Object to return a jwt token to client on Rest call authentification
 * @author Titouan
 *
 */
public class AuthentificationResponse {

	private final String jwt;
	private final String firstname;
	private final String lastname;
	
	public AuthentificationResponse(String jwt, String firstname, String lastname) {
		this.jwt = jwt;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getJwt() {
		return jwt;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}
	
}
