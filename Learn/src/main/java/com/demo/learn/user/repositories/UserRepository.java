package com.demo.learn.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.learn.user.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUserId(Long userId);

}
