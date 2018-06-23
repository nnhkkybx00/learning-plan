package com.dl.validatetest.controller;


import com.dl.validatetest.controller.service.PersonService;
import com.dl.validatetest.vo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(produces = {"application/json;charset=UTF-8"})
@Validated
public class PersonController {

  @Autowired
  PersonService personService;

  @RequestMapping(value = "add",method = RequestMethod.POST)
  public String add(@RequestBody /*@Valid*/ @Validated({Person.Add.class, Default.class}) Person person/*,BindingResult bindingResult*/) {

//    if (!bindingResult.getAllErrors().isEmpty()) {
//      for (ObjectError objectError : bindingResult.getAllErrors()) {
//        System.out.println(objectError.getDefaultMessage());
//      }
//    }

    return "添加成功";
  }
  @RequestMapping(value = "update")
  public String udpate(@RequestBody @Validated({Person.Update.class}) Person person) {


    return "更新成功";
  }

  @RequestMapping(value = "batch_update")
  @Validated({Person.Update.class})
  public String udpate(@RequestBody @Valid List<Person> person) {


    return "批量更新成功";
  }


  @RequestMapping(value = "del")
  public String del(@NotNull @Min(100000) Integer id) {


    return "删除成功";
  }

  @RequestMapping(value = "get")
  public String get(@NotNull Integer id,Map<@NotNull  String,@Min(100) Integer> data) {


    return "查询成功";
  }


  @RequestMapping(value = "service_add")
  public String get(Person person) {

    personService.addPerson(person);
    return "查询成功";
  }




}
