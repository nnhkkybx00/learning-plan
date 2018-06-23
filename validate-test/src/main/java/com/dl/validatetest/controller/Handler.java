package com.dl.validatetest.controller;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class Handler {

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public String handlerException(Exception e) {

    return "";
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public String handlerException(MethodArgumentNotValidException e) {
    String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

    return defaultMessage;
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseBody
  public String handlerException(ConstraintViolationException e) {

    Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

    for (ConstraintViolation<?> constraintViolation : constraintViolations) {
      System.out.println(constraintViolation.getMessage());
    }
    return "";
  }






}
