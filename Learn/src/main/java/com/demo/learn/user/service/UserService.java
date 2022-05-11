package com.demo.learn.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.learn.user.entities.User;
import com.demo.learn.user.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User addUser(User user) {
		log.info("Inside addUser method of UserService");
		return userRepository.save(user);
	}
	
	public User getUserById(Long userId) throws Exception {
		log.info("Inside getUserById method of UserService");
		
		User user = userRepository.findByUserId(userId);
		if(user == null) {
			// TODO: Exception handling
		}
		return user;
	}

}
