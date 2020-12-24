package com.woniuxy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.entity.Product;
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
}
