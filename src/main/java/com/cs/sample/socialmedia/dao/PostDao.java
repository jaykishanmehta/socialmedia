package com.cs.sample.socialmedia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cs.sample.socialmedia.model.Post;

public interface PostDao extends CrudRepository<Post, Integer> {

	Iterable<Post> findByUserIdOrderByIdDesc(int userId);

//	@Query("select " 
//			+ "new com.cs.sample.socialmedia.model.Post(p.id, p.user_id, p.content)" +" from posts p where user_id in (select following_id from followers where user_id = :id) order by p.id desc") 
//	List<Post> findUserFeeds(@Param("id") Integer id);

}
