package com.cs.sample.socialmedia.dao;

import org.springframework.data.repository.CrudRepository;

import com.cs.sample.socialmedia.model.User;

public interface UserDao extends CrudRepository<User, Integer> {

}
