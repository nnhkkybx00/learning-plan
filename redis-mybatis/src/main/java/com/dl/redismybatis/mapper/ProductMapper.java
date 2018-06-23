package com.dl.redismybatis.mapper;

import com.dl.redismybatis.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface ProductMapper {

    Product select(long id);

    void update(Product product);
}
