package com.cs.sample.socialmedia.model;

import java.util.List;

public class UserFeedVO {

	private int id;
	private String firstName;
	private String lastName;
	
	private List<Post> userPosts;
	
	private List<User> following;
	
	private List<Post> feeds;

	public UserFeedVO() {
		
	}
	
	public UserFeedVO(User user) {
		super();
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
	}
	
	public UserFeedVO(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Post> getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(List<Post> userPosts) {
		this.userPosts = userPosts;
	}

	public List<User> getFollowing() {
		return following;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public List<Post> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<Post> feeds) {
		this.feeds = feeds;
	}

	@Override
	public String toString() {
		return "UserFeedVO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userPosts="
				+ userPosts + ", following=" + following + ", feeds=" + feeds + "]";
	}
	
}
