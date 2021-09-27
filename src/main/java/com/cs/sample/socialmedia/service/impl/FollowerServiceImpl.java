package com.cs.sample.socialmedia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.sample.socialmedia.dao.FollowerDao;
import com.cs.sample.socialmedia.model.Follower;
import com.cs.sample.socialmedia.service.FollowerService;

@Service
public class FollowerServiceImpl implements FollowerService {

	@Autowired
	FollowerDao followerDas;
	
	@Override
	public Iterable<Follower> getAllFollowings(int userId) {
		return followerDas.findByUserId(userId);
	}

	@Override
	public Follower create(Follower newFollower) {
		return followerDas.save(newFollower);
	}

	@Override
	public boolean isFollowerExist(Follower newFollower) {
		Iterable<Follower> follower = followerDas.findByUserIdAndFollowingId(newFollower.getUserId(), newFollower.getFollowingId());
		
		if(follower.iterator().hasNext()) {
			return true;
		}
		
		return false;
	}

	@Override
	public void remove(Follower follower) {
		followerDas.deleteByUserIdAndFollowingId(follower.getUserId(), follower.getFollowingId());
	}

}
