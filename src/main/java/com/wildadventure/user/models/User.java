package com.wildadventure.user.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Bean representing a user of Wild Adventure to be store in DB
 * @author Titouan
 *
 */
@Entity
@Table(name="userWa")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private Long id;
	
	@Column(name="MAIL", unique=true)
	private String mail;
	
	@Column(name="FIRSTNAME")
	private String firstname;
	
	@Column(name="LASTNAME")
	private String lastname;
	
	@Column(name="PASSWORD")
	private String password;
	
	public User() {
		
	}

	public User(Long id, String mail, String firstname, String lastname, String password) {
		super();
		this.id = id;
		this.mail = mail;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", mail=" + mail + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", password=" + password + "]";
	}
}
