package com.cs.sample.socialmedia.service;

import com.cs.sample.socialmedia.model.User;

public interface UsersService {
	Iterable<User> getAllUsers();

	User create(User newUser);

	User getUserById(Integer id);
}
