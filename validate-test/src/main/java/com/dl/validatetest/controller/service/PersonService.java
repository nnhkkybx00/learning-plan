package com.dl.validatetest.controller.service;

import com.dl.validatetest.vo.Person;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class PersonService {

  @Validated({Person.Add.class})
//  @Transaction
  public void addPerson(@Valid Person person){

    System.out.printf("添加成功");
  }

}
