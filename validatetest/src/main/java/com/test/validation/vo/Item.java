package com.test.validation.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

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
