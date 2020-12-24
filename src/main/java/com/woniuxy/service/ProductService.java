package com.woniuxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.woniuxy.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.vo.PageVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */
public interface ProductService extends IService<Product> {

    IPage<Product> queryProductInfo(PageVo pageVo);
}
