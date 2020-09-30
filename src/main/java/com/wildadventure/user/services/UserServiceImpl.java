package com.wildadventure.user.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wildadventure.user.dao.IUserDao;
import com.wildadventure.user.models.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao dao;
	
	@Override
	public Optional<User> getById(Long id) {
		return dao.findById(id);
	}

	@Override
	public User getByMail(String mail) {
		return dao.findByMail(mail);
	}

	@Override
	public User add(User newUser) {
		return dao.save(newUser);
	}

}
