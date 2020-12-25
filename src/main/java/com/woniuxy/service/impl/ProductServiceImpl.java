package com.woniuxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.entity.Product;
import com.woniuxy.entity.ProductAttrGroup;
import com.woniuxy.mapper.ProductMapper;
import com.woniuxy.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.vo.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public IPage<Product> queryProductInfo(PageVo pageVo) {
        Page<Product> page = new Page<>(pageVo.getCurrent(),pageVo.getSize());
        IPage<Product> productIPage = productMapper.queryProductInfo(page);
        return productIPage;
    }

    @Override
    public IPage<Product> fuzzyProductInfo(PageVo pageVo, String productName, String productStatus, String username) {
        Page<Product> page = new Page<>(pageVo.getCurrent(),pageVo.getSize());
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("p.deleted",0);
        if (productName != ""){
            System.out.println("进入模糊名称");
            wrapper.like("p.product_name",productName);
        }
        if (!productStatus.equals("全部")){
            System.out.println(productStatus);
            wrapper.eq("p.product_status",productStatus);
        }
        if (username != ""){
            System.out.println("进入模糊操作人");
            wrapper.like("iu.username",username).or();
        }
        IPage<Product> productIPage = productMapper.fuzzyProductInfo(page, wrapper);
        return productIPage;
    }
}
