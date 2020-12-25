package com.woniuxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniuxy.entity.ProductAttrConf;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.vo.PageVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuHongTao
 * @since 2020-12-22
 */
public interface ProductAttrConfService extends IService<ProductAttrConf> {
    IPage<ProductAttrConf> findAttrInfo(PageVo pageVo);
}
