package com.susan.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.susan.app.entity.User;


public interface UserRepository extends CrudRepository<User, String> {
	public User findByUsername(String username);
}