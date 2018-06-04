package com.dl.cloud.msconsumerorderribbonhystrixcustomizing.user.controller;

import com.dl.cloud.msconsumerorderribbonhystrixcustomizing.user.command.OrderServiceCommand;
import com.dl.cloud.msconsumerorderribbonhystrixcustomizing.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class OrderController {
  private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/user/{id}")
  public User findById(@PathVariable Long id) {
	  OrderServiceCommand command = new OrderServiceCommand("orederGroup", restTemplate, id);
	  User user = command.execute();
      return user;
  }

}
