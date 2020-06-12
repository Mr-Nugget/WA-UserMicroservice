package com.wildadventure.user.models;

/**
 * Object to get input values of a RestCall of authentification
 * @author Titouan
 *
 */
public class AuthentificationRequest {
	private String mail;
	private String password;
	
	public AuthentificationRequest() {
		
	}
		
	public AuthentificationRequest(String mail, String password) {
		super();
		this.mail = mail;
		this.password = password;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
