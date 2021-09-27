package com.cs.sample.socialmedia.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cs.sample.socialmedia.model.Post;

public interface PostDao extends CrudRepository<Post, Integer> {

	Iterable<Post> findByUserIdOrderByIdDesc(int userId);

	@Query(value = "select * from posts p " 
			+ "where user_id in (select following_id from followers where user_id = :id) or user_id = :id "
			, countQuery  = "select COUNT(*) from posts p " 
					+ "where user_id in (select following_id from followers where user_id = :id) or user_id = :id "
			, nativeQuery=true) 
	Page<Post> findUserFeeds(@Param("id") Integer id, Pageable of);

}
