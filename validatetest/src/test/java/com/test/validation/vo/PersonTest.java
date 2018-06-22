package com.test.validation.vo;

import static org.junit.Assert.*;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.Before;
import org.junit.Test;

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