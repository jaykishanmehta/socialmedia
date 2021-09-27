package com.cs.sample.socialmedia.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.cs.sample.socialmedia.service.FollowerService;
import com.cs.sample.socialmedia.service.PostService;
import com.cs.sample.socialmedia.service.UsersService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/useractivity")
@Api(value="useractivites", description="Operations pertaining to users in social media app")
public class UserActivityController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	PostService postService;

	@Autowired
	FollowerService followerService;
	
	@ApiOperation(value = "Get all the users",response = Iterable.class)
	@GetMapping("/users")
	public Iterable<User> read() {
		return usersService.getAllUsers();
	}
	
	@ApiOperation(value = "Create new user",response = User.class)
	@PostMapping("/newUser")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
    User createNewUser(@RequestBody User newUser) {
        return usersService.create(newUser);
    }
	
	@ApiOperation(value = "Create new post",response = Post.class)
	@PostMapping("/newPost")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	Post createNewPost(@RequestBody Post newPost) {
        return postService.create(newPost);
    }
	
	@ApiOperation(value = "Follow user",response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Started following ..."),
            @ApiResponse(code = 208, message = "Already following to this user")
    })
	@PostMapping("/newFollower")
	ResponseEntity<String> createNewFollower(@RequestBody Follower newFollower) {
		if(followerService.isFollowerExist(newFollower)) {
			return new ResponseEntity<String>("Already following to this user", HttpStatus.ALREADY_REPORTED);
		}
		
		followerService.create(newFollower);
		return new ResponseEntity<String>("Started following " + newFollower.getFollowingId(), HttpStatus.OK);
    }
	
	@ApiOperation(value = "UnFollow user",response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Already not following to this user")
    })
	@DeleteMapping("/unfollow")
	ResponseEntity<String> unfollowUser(@RequestBody Follower follower) {
		if(!followerService.isFollowerExist(follower)) {
			return new ResponseEntity<String>("Already not following to this user", HttpStatus.BAD_REQUEST);
		}
		
		followerService.remove(follower);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
	
	@ApiOperation(value = "Generate users landing page contains all required information related to user",response = ResponseEntity.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "User does not exist")
    })
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
