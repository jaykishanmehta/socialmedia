package com.cs.sample.socialmedia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cs.sample.socialmedia.dao.PostDao;
import com.cs.sample.socialmedia.model.Post;
import com.cs.sample.socialmedia.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostDao postDas;
	
	@Value( "${user.feed.limit}" )
	private Integer feedLimit;
	
	@Override
	public Iterable<Post> getAllPosts(int userId) {
		return postDas.findByUserIdOrderByIdDesc(userId);
	}

	@Override
	public Post create(Post newPost) {
		return postDas.save(newPost);
	}

	@Override
	public Page<Post> getUserFeed(Integer id) {
		return postDas.findUserFeeds(id, PageRequest.of(0, feedLimit, Sort.by("id").descending()));
	}
	
}
