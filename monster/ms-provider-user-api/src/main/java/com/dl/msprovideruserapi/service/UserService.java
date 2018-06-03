package com.dl.msprovideruserapi.service;

import com.dl.msprovideruserapi.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public interface UserService {

  @GetMapping("/user/{id}")
  public User getUser(@PathVariable(value = "id") Long id);
  
}
