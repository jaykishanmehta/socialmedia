package com.cs.sample.socialmedia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.cs.sample.socialmedia.model.Follower;
import com.cs.sample.socialmedia.model.Post;
import com.cs.sample.socialmedia.model.User;
import com.cs.sample.socialmedia.service.FollowerService;
import com.cs.sample.socialmedia.service.PostService;
import com.cs.sample.socialmedia.service.UsersService;

@RunWith(MockitoJUnitRunner.class)
public class UserActivityControllerTest {

	@Mock
	UsersService usersService;
	
	@Mock
	PostService postService;

	@Mock
	FollowerService followerService;
	
	@InjectMocks
	UserActivityController userActivityController;
	
	@Before
	public void setUp() throws Exception {
		Mockito.when(usersService.getAllUsers()).thenReturn(getUserList());
		Mockito.when(usersService.create(Mockito.any())).thenReturn(new User(99, "Test", "User"));
		Mockito.when(postService.create(Mockito.any())).thenReturn(new Post(99, 5, "User"));
		Mockito.when(followerService.create(Mockito.any())).thenReturn(new Follower(99, 5, 4));
	}

	private Iterable<User> getUserList() {
		List<User> userList = new ArrayList<User>();
		userList.add(new User(1, "Test", "User"));
		userList.add(new User(2, "Test", "User2"));
		return userList;
	}

	@Test
	public void testRead() {
		Assertions.assertThat(userActivityController.read())
			.hasSize(2)
			.extracting(User::getFirstName).allMatch(t -> t.equals("Test"));
	}

	@Test
	public void testCreateNewUser() {
		Assertions.assertThat(userActivityController.createNewUser(new User("Test", "User3")))
		.extracting(User::getId).isEqualTo(99);
	}

	@Test
	public void testCreateNewPost() {
		Assertions.assertThat(userActivityController.createNewPost(new Post(5, "Content")))
		.extracting(Post::getId).isEqualTo(99);
	}

	@Test
	public void testCreateNewFollower() {
		Mockito.when(followerService.isFollowerExist(Mockito.any())).thenReturn(false);
		ResponseEntity<String> r = userActivityController.createNewFollower(new Follower(5,4));
		assertEquals("Started following 4", r.getBody());
	}
	
	@Test
	public void testCreateNewFollowerValidation() {
		Mockito.when(followerService.isFollowerExist(Mockito.any())).thenReturn(true);
		ResponseEntity<String> r = userActivityController.createNewFollower(new Follower());
		assertEquals("Already following to this user", r.getBody());
	}

	@Test
	public void testUnfollowUser() {
		Mockito.when(followerService.isFollowerExist(Mockito.any())).thenReturn(true);
		ResponseEntity<String> r = userActivityController.unfollowUser(new Follower());
		assertEquals("Success", r.getBody());
	}
	
	@Test
	public void testUnfollowUserValidation() {
		Mockito.when(followerService.isFollowerExist(Mockito.any())).thenReturn(false);
		ResponseEntity<String> r = userActivityController.unfollowUser(new Follower());
		assertEquals("Already not following to this user", r.getBody());
	}

	@Test
	public void testGenerateFeed() {
		Mockito.when(usersService.getUserById(Mockito.anyInt())).thenReturn(new User());
		ResponseEntity<?> r = userActivityController.generateFeed(3);
		Map<String, Object> out = (Map<String, Object>) r.getBody();
		Assertions.assertThat(out).containsKeys("user", "posts", "followers", "feed", "msg");
		assertEquals(out.get("msg"), "Success");
	}
	
	@Test
	public void testGenerateFeedValidation() {
		Mockito.when(usersService.getUserById(Mockito.anyInt())).thenReturn(null);
		ResponseEntity<?> r = userActivityController.generateFeed(3);
		Map<String, Object> out = (Map<String, Object>) r.getBody();
		
		assertEquals("User does not exist", out.get("msg"));
	}

}
