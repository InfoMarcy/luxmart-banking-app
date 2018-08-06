package com.openshift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openshift.model.User;
import com.openshift.model.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class RestUserController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping()
	public List<User>  home() {
		
		 return userRepository.findAll();

	}
	
	@RequestMapping("/{username}")
	public User  getUserById(@PathVariable String username) {
		
		 return userRepository.findByUsername(username);

	}


} 