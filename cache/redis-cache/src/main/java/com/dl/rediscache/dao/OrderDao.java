package com.dl.rediscache.dao;

import com.dl.rediscache.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {

    List<Product> getAll();
}
