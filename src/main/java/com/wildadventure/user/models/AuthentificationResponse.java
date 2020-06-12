package com.wildadventure.user.models;

/**
 * Object to return a jwt token to client on Rest call authentification
 * @author Titouan
 *
 */
public class AuthentificationResponse {

	private final String jwt;
	
	public AuthentificationResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
	
	
}
