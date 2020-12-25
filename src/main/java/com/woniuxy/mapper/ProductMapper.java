package com.woniuxy.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.entity.ProductAttrGroup;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */
public interface ProductMapper extends BaseMapper<Product> {

    IPage<Product> queryProductInfo(Page<Product> page);

    IPage<Product> fuzzyProductInfo(Page<Product> page,@Param(Constants.WRAPPER) Wrapper<Product> wrapper);
}
