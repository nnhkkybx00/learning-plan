package com.dl.msprovideruser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dl.msprovideruserapi.entity.User;
import com.dl.msprovideruser.repository.UserRepository;
import com.dl.msprovideruserapi.service.UserService;

@RestController
public class RefactorUserController implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
    public User getUser(@PathVariable Long id) {
		User findOne = userRepository.findOne(id);
	    return findOne;
    }

}