package com.dl.validatetest.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by xx on 2018/6/16.
 */
@Data
public class Item {

  @NotBlank
  private String itemName;

  @NotNull
  private Integer count;

}
