package com.test.validation.controller.service;

import com.test.validation.vo.Person;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class PersonService {

  @Validated({Person.Add.class})
//  @Transaction
  public void addPerson(@Valid Person person){

    System.out.printf("添加成功");
  }

}
