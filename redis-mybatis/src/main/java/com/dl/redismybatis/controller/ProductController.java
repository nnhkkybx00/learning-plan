package com.dl.redismybatis.controller;

import com.dl.redismybatis.entity.Product;
import com.dl.redismybatis.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/{id}")
    public Product getProductInfo(@PathVariable("id") Long productId){
        return productMapper.select(productId);
    }

    @PutMapping("/{id}")
    public void updateProductInfo(@PathVariable("id") Long productId,@RequestBody Product product){

         productMapper.update(product);
    }
}
