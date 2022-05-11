package com.demo.learn.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.learn.user.entities.User;
import com.demo.learn.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Long userId) throws Exception {
		return userService.getUserById(userId);
	}
}
