package com.dl.validatetest.vo;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by xx on 2018/6/16.
 */
public class PersonTest {
  Validator validator;

  @Before
  public void setUp() throws Exception {
    validator= Validation.buildDefaultValidatorFactory().getValidator();

  }

  @Test
  public void testAdd() throws Exception {

    Person person = new Person();

    Set<ConstraintViolation<Person>> validate = validator.validate(person);
    for (ConstraintViolation<Person> constraintViolation : validate) {
      System.out.println(constraintViolation.getMessage());
    }

  }

}