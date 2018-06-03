package com.dl.msconsumerorderfeign.user.controller;

import com.dl.msconsumerorderfeign.user.entity.User;
import com.dl.msconsumerorderfeign.user.feign.RefactorUserService;
import com.dl.msconsumerorderfeign.user.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {
  @Autowired
  private UserFeignClient userFeignClient;
  @Autowired
  private RefactorUserService refactorUserService;

  @GetMapping("/user/{id}")
  public User findById(@PathVariable Long id) {
    return userFeignClient.findById(id);
  }
  
  @GetMapping("/user-extends/{id}")
  public com.tuling.cloud.study.entity.User findById2(@PathVariable Long id) {
	  return refactorUserService.getUser(id);
  }
  
}
