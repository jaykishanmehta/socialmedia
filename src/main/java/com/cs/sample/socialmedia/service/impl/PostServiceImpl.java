package com.cs.sample.socialmedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.sample.socialmedia.dao.PostDao;
import com.cs.sample.socialmedia.model.Post;
import com.cs.sample.socialmedia.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostDao postDas;
	
	@Override
	public Iterable<Post> getAllPosts(int userId) {
		return postDas.findByUserIdOrderByIdDesc(userId);
	}

	@Override
	public Post create(Post newPost) {
		return postDas.save(newPost);
	}

	@Override
	public List<Post> getUserFeed(Integer id) {
//		return postDas.findUserFeeds(id);
		return null;
	}
	
}
