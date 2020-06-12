package com.wildadventure.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wildadventure.user.dao.IUserDao;
import com.wildadventure.user.models.MyUserPrincipal;
import com.wildadventure.user.models.User;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private IUserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		User user = userDao.findByMail(mail);
		if(user == null) {
			throw new UsernameNotFoundException(mail);
		}else {
			return new MyUserPrincipal(user);
		}
	}

}
