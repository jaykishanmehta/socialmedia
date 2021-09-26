package com.cs.sample.socialmedia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.sample.socialmedia.dao.UserDao;
import com.cs.sample.socialmedia.model.User;
import com.cs.sample.socialmedia.service.UsersService;

@Service
public class UserServiceImpl implements UsersService {

	@Autowired
	UserDao userDas;
	
	@Override
	public Iterable<User> getAllUsers() {
		return userDas.findAll();
	}

	@Override
	public User create(User newUser) {
		return userDas.save(newUser);
	}

	@Override
	public User getUserById(Integer id) {
		return userDas.findById(id).orElse(null);
	}
	
}
