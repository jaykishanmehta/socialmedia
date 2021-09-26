package com.cs.sample.socialmedia.service;

import java.util.List;

import com.cs.sample.socialmedia.model.Post;

public interface PostService {
	Iterable<Post> getAllPosts(int userId);

	Post create(Post newPost);

	List<Post> getUserFeed(Integer id);
}
