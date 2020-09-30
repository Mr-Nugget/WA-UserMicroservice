package com.wildadventure.user.services;

import java.util.Optional;

import com.wildadventure.user.models.User;

public interface IUserService {
	
	public Optional<User> getById(Long id);
	
	public User getByMail(String mail);
	
	public User add(User newUser);
	
}
