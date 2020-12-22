package com.woniuxy.controller;


import com.woniuxy.entity.Product;
import com.woniuxy.mapper.ProductMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductMapper productMapper;




}

