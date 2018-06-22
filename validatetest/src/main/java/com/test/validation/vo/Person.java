package com.test.validation.vo;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

@Data
public class Person {

  @NotNull(groups = {Update.class})
  private Integer id;


  @NotBlank(groups = {Add.class},message = "{name.not.null}")
  private String name;

  @NotNull(groups = {Add.class})
  @Range(min = 18, max = 100, groups = {Add.class, Update.class})
  private Integer age;

  @NotNull(groups = {Add.class})
  @Email(groups = {Add.class, Update.class})
  private String email;


  private String remark;

  @Valid
  private Item item;


  public interface Add {

  }

  public interface Update {

  }



}
