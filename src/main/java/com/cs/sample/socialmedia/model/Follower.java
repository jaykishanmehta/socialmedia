package com.cs.sample.socialmedia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FOLLOWERS")
public class Follower {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int userId;
	private int followingId;
	
	public Follower() {
	}
	
	public Follower(int id, int userId, int followingId) {
		super();
		this.id = id;
		this.userId = userId;
		this.followingId = followingId;
	}

	public Follower(int userId, int followingId) {
		super();
		this.userId = userId;
		this.followingId = followingId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFollowingId() {
		return followingId;
	}
	public void setFollowingId(int followingId) {
		this.followingId = followingId;
	}
	@Override
	public String toString() {
		return "Follower [id=" + id + ", userId=" + userId + ", followingId=" + followingId + "]";
	}
	
}
