package com.woniuxy.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.entity.ProductAttrGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-20
 */
public interface ProductAttrGroupMapper extends BaseMapper<ProductAttrGroup> {

    IPage<ProductAttrGroup> findAttrGroup(Page<ProductAttrGroup> page);

    IPage<ProductAttrGroup> fuzzyAttrGroup(Page<ProductAttrGroup> page,@Param(Constants.WRAPPER) Wrapper<ProductAttrGroup> wrapper);

}
