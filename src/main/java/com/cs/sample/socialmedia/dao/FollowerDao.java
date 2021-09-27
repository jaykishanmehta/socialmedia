package com.cs.sample.socialmedia.dao;

import org.springframework.data.repository.CrudRepository;

import com.cs.sample.socialmedia.model.Follower;

public interface FollowerDao extends CrudRepository<Follower, Integer> {

	Iterable<Follower> findByUserId(int userId);

	Iterable<Follower> findByUserIdAndFollowingId(int userId, int followingId);

	void deleteByUserIdAndFollowingId(int userId, int followingId);

}
