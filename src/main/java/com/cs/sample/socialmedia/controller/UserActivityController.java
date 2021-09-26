package com.cs.sample.socialmedia.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cs.sample.socialmedia.model.Follower;
import com.cs.sample.socialmedia.model.Post;
import com.cs.sample.socialmedia.model.User;
import com.cs.sample.socialmedia.model.UserFeedVO;
import com.cs.sample.socialmedia.service.FollowerService;
import com.cs.sample.socialmedia.service.PostService;
import com.cs.sample.socialmedia.service.UsersService;

@RestController
@RequestMapping(path = "/useractivity")
public class UserActivityController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	PostService postService;

	@Autowired
	FollowerService followerService;
	
	@GetMapping("/users")
	public Iterable<User> read() {
		return usersService.getAllUsers();
	}
	
	@PostMapping("/newUser")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
    User createNewUser(@RequestBody User newUser) {
        return usersService.create(newUser);
    }
	
	@PostMapping("/newPost")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	Post createNewPost(@RequestBody Post newPost) {
        return postService.create(newPost);
    }
	
	@PostMapping("/newFollower")
	ResponseEntity<String> createNewFollower(@RequestBody Follower newFollower) {
		if(followerService.isFollowerExist(newFollower)) {
			return new ResponseEntity<String>("Already following to this user", HttpStatus.ALREADY_REPORTED);
		}
		
		followerService.create(newFollower);
		return new ResponseEntity<String>("Started following " + newFollower.getFollowingId(), HttpStatus.OK);
    }
	
	@GetMapping("/userfeed/{id}")
	public ResponseEntity<?> generateFeed(@PathVariable Integer id) {
		User user = usersService.getUserById(id);
		
		Map<String, Object> result = new HashMap<String,Object>();
		
		if(user == null) {
			result.put("msg", "User does not exist");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);			
		}
		
		result.put("user", user);
		result.put("posts", postService.getAllPosts(id));
		result.put("followers", followerService.getAllFollowings(id));
		result.put("feed", postService.getUserFeed(id));
		result.put("msg", "Success");
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
}
