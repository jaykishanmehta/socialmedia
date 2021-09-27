package com.cs.sample.socialmedia.service;

import com.cs.sample.socialmedia.model.Follower;

public interface FollowerService {
	
	Iterable<Follower> getAllFollowings(int userId);

	Follower create(Follower newFollower);

	boolean isFollowerExist(Follower newFollower);

	void remove(Follower follower);
}
