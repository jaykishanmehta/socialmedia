package com.cs.sample.socialmedia.service;

import org.springframework.data.domain.Page;

import com.cs.sample.socialmedia.model.Post;

public interface PostService {
	Iterable<Post> getAllPosts(int userId);

	Post create(Post newPost);

	Page<Post> getUserFeed(Integer id);
}
