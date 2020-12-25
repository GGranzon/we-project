package com.woniuxy.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.entity.ProductAttrConf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-22
 */
public interface ProductAttrConfMapper extends BaseMapper<ProductAttrConf> {

    IPage<ProductAttrConf> findAttrInfo(Page<ProductAttrConf> page);

}
