package com.wildadventure.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildadventure.user.models.User;

@Repository
public interface IUserDao extends JpaRepository<User, Long>{
	
	public User findByMail(String mail);
}
